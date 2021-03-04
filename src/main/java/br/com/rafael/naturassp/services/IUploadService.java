package br.com.rafael.naturassp.services;

import org.springframework.web.multipart.MultipartFile;

public interface IUploadService {
	
	public String uploadService(MultipartFile arquivo);

}
