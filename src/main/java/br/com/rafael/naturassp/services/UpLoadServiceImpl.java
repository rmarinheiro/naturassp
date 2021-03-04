package br.com.rafael.naturassp.services;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class UpLoadServiceImpl implements IUploadService {

	/*
	 * metodo responsavel em copiar o arquivo via requisição para uma pasta especifica
	 * e retornar o caminho dele.Caso haja erro retornar NULL
	 */
	@Override
	public String uploadService(MultipartFile arquivo) {
		try {
			
			System.out.println("DEBUG " + arquivo.getOriginalFilename());
			String caminho = "C:\\Users\\rsmar\\Pictures\\Saved Pictures";
			Path path= Paths.get(caminho + File.separator + arquivo.getOriginalFilename());
			Files.copy(arquivo.getInputStream(),path,StandardCopyOption.REPLACE_EXISTING);
			System.out.println("DEBUG -- Arquivo copiado");
			return path.toString();
		} catch (Exception ex) {
			System.out.println("Ocorreu erro ao carregar a imagem" + ex.getMessage());
		}
		return null;
	}

}
