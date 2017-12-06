package com.ureactor.jeesite.common.service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.common.utils.IOUtils;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.ObjectMetadata;
import com.ureactor.jeesite.common.config.OSSConfig;
import com.ureactor.jeesite.common.utils.DateUtils;
import com.ureactor.jeesite.common.utils.FileUtils;
import com.ureactor.jeesite.common.utils.IdGen;
import com.ureactor.jeesite.common.utils.OSSContentTypeUtil;
import com.ureactor.jeesite.common.utils.StringUtils;

/**
 * 阿里云OSS文件上传
 * 
 * @author wangshichuan@uworks.cc
 *
 */
@Service("ossService")
public class OssService {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Resource
	private OSSConfig ossConfig;
	@Autowired
	private OSSClient oSSClient;

	/**
	 * oss上传文件，返回文件访问路径
	 * 
	 * @param file：文件
	 * @return
	 */
	public String upload(MultipartFile file) {
		String originFileName = file.getOriginalFilename();
		String suffixName = originFileName.substring(originFileName.indexOf(".") + 1);
		String fileType = OSSContentTypeUtil.getContentType(suffixName);
		// 设置文件名
		String filePathName = generateRelativeStoragePath(suffixName);
		byte[] fileContent = null;
		try {
			fileContent = file.getBytes();
		} catch (Exception e) {
			logger.error("Cannot get file content from {}.", originFileName);
			// ExceptionUtil.throwException(Errors.SYSTEM_CUSTOM_ERROR.code,
			// "不能读取" + originFileName + "内容");
		}
		ObjectMetadata meta = new ObjectMetadata();
		// 设置上传文件长度
		meta.setContentLength(file.getSize());
		// 设置上传MD5校验
		String md5 = BinaryUtil.toBase64String(BinaryUtil.calculateMd5(fileContent));
		meta.setContentMD5(md5);
		meta.setContentType(fileType);

		// 存储
		try {
			oSSClient.putObject(ossConfig.getBucketName(), filePathName, file.getInputStream(), meta);
		} catch (OSSException | ClientException | IOException e) {
			logger.error("OSS storage error", e);
			// ExceptionUtil.throwException(Errors.SYSTEM_CUSTOM_ERROR.code,
			// "OSS storage exception");
		}
		String path = ossConfig.getDownloadEndpoint() + FileUtils.getFileSeparator() + filePathName;
		if (FileUtils.isImg(suffixName)) {
			// 图片访问处理样式，可在oss自定义,缩放、裁剪、压缩、旋转、格式、锐化、水印等
			path += StringUtils.isNotBlank(ossConfig.getStyleName())
					? "?x-oss-process=style/" + ossConfig.getStyleName() : "";
		}
		return path;
	}

	/**
	 * base64code方式上传
	 * 
	 * @param imageBytes
	 *            文件流
	 * @return
	 */
	public String uploadImageBase64(byte[] imageBytes) {
		String fileType = "image/jpeg";
		// 设置文件名
		String filePathName = generateRelativeStoragePath("jpeg");
		ObjectMetadata meta = new ObjectMetadata();
		// 设置上传文件长度
		meta.setContentLength(imageBytes.length);
		// 设置上传MD5校验
		String md5 = BinaryUtil.toBase64String(BinaryUtil.calculateMd5(imageBytes));
		meta.setContentMD5(md5);
		meta.setContentType(fileType);

		// 存储
		try {
			oSSClient.putObject(ossConfig.getBucketName(), filePathName, new ByteArrayInputStream(imageBytes), meta);
		} catch (Exception e) {
			logger.error("OSS storage error", e);
			// ExceptionUtil.throwException(Errors.SYSTEM_CUSTOM_ERROR.code,
			// "OSS storage exception");
		}
		String path = ossConfig.getDownloadEndpoint() + FileUtils.getFileSeparator() + filePathName;
		// 图片访问处理样式，可在oss自定义,缩放、裁剪、压缩、旋转、格式、锐化、水印等
		return path + (StringUtils.isNotBlank(ossConfig.getStyleName())
				? "?x-oss-process=style/" + ossConfig.getStyleName() : "");
	}

	/**
	 * File方式上传
	 * 
	 * @param file
	 *            文件
	 * @return
	 */
	public String uploadFile(File file) {
		// 存储
		InputStream is = null;
		try {
			String originFileName = file.getName();
			String suffixName = originFileName.substring(originFileName.indexOf(".") + 1);
			String fileType = OSSContentTypeUtil.getContentType(suffixName);
			// String fileType = "application/octet-stream";
			// 设置文件名
			String filePathName = generateRelativeStoragePath(suffixName);
			is = new FileInputStream(file);
			byte[] fileContent = new byte[is.available()];
			is.read(fileContent);
			ObjectMetadata meta = new ObjectMetadata();
			// 设置上传文件长度
			meta.setContentLength(file.length());
			// 设置上传MD5校验
			String md5 = BinaryUtil.toBase64String(BinaryUtil.calculateMd5(fileContent));
			meta.setContentMD5(md5);
			meta.setContentType(fileType);
			oSSClient.putObject(ossConfig.getBucketName(), filePathName, new ByteArrayInputStream(fileContent), meta);
			String path = ossConfig.getDownloadEndpoint() + FileUtils.getFileSeparator() + filePathName;
			return path;
		} catch (Exception e) {
			logger.error("OSS storage error", e);
			// ExceptionUtil.throwException(Errors.SYSTEM_CUSTOM_ERROR.code,
			// "OSS storage exception");
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	/**
	 * 下载文件
	 * 
	 * @param url
	 * @return
	 */
	public byte[] download(String url) {
		InputStream is = null;
		try {
			String key = url.split(ossConfig.getDownloadEndpoint() + "/")[1];
			OSSObject ossObject = oSSClient.getObject(ossConfig.getBucketName(), key);
			is = ossObject.getObjectContent();
			byte[] data = IOUtils.readStreamAsByteArray(is);
			return data;
		} catch (Exception e) {
			logger.error("下载文件异常,url={}", url, e);
			e.printStackTrace();
			// ExceptionUtil.throwException(Errors.SYSTEM_CUSTOM_ERROR.code,
			// "下载文件异常");
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	/**
	 * 获取存储的相对路径 规则path + / + yyyyMMddHH + uuid
	 * 
	 * @param suffixName
	 *            后缀名
	 * @return
	 */
	private String generateRelativeStoragePath(String suffixName) {
		String time = DateUtils.getDate("yyyyMMddHH");
		String uuid = IdGen.uuidWith_();
		StringBuilder sb = new StringBuilder();
		String storagePath = this.ossConfig.getStoragePath();
		if (StringUtils.isNotBlank(storagePath)) {
			sb.append(storagePath).append("/");
		}
		sb.append(time).append(uuid);
		if (StringUtils.isNotBlank(suffixName)) {
			sb.append(".").append(suffixName);
		}
		return sb.toString();
	}

}
