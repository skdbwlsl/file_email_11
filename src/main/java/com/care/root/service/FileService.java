package com.care.root.service;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface FileService {
	public static final String IMAGE_REPO = "C:/spring/image_repo";   //이미지 저장소 :이경로에 파일저장
	public void fileProcess(MultipartHttpServletRequest mul);
	public void getShoesImage(Model model);

}
