package com.remd.remd_articles.utils;

import org.springframework.core.io.ClassPathResource;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;;

public  class Utils {
    public static String addMultiPartFile(String locationName, MultipartFile multipartFile) throws IOException {
        final String folder = new ClassPathResource("static/"+locationName).getFile().getAbsolutePath();
        final String route = ServletUriComponentsBuilder.fromCurrentContextPath().path("/"+locationName +"/").path(multipartFile.getOriginalFilename()).toUriString();
        byte [] fileData = multipartFile.getBytes();
        Path path = Paths.get(folder + File.separator + multipartFile.getOriginalFilename());
        Files.write(path,fileData);
        System.out.println(route);
        return  "/" + locationName + "/" + multipartFile.getOriginalFilename();
    }
    public static Boolean verifyImageExtension(MultipartFile multipartFile){
        return (!multipartFile.getContentType().equals("image/jpeg") && !multipartFile.getContentType().equals("image/png"));
    }

    public static Boolean verifyFileExtensionType(MultipartFile multipartFile){
        return  (!multipartFile.getContentType().equals("application/pdf"));
    }

}
