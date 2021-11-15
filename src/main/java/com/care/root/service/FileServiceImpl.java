package com.care.root.service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.care.root.dto.ShoesDTO;
import com.care.root.mybatis.FileMapper;

@Service
public class FileServiceImpl implements FileService{
	@Autowired FileMapper fm;
	
	public void fileProcess(MultipartHttpServletRequest mul) {//오버라이딩
		ShoesDTO dto = new ShoesDTO();//dto만들어서	
		dto.setId(mul.getParameter("id"));//id넣고
		dto.setName(mul.getParameter("name"));//name넣어준다

		MultipartFile file =mul.getFile("file");
		if(file.getSize() != 0) { //file.isEmpty()와 같다
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss-");//시간
			Calendar calendar = Calendar.getInstance();//켈린더
			String sysFileName = format.format(calendar.getTime());//현재 시간 format에 넣기
			//2021111541-파일이름
			sysFileName+= file.getOriginalFilename();//시간형식 뒤에 우리가 가져온 오리지널 파일이름 붙이기
			
			dto.setImgName(sysFileName);//만약 파일이 있다면 이미지 데이터를 db에 변경된 이름(sysFileName)으로 저장한다
			
			
			File saveFile =  new File(IMAGE_REPO+"/"+sysFileName);
			
		try {
			file.transferTo(saveFile);//transferTo :바로 저장가능
		} catch (Exception e) {
			e.printStackTrace();
		}
		}else {
			dto.setImgName("nan");//파일을 선택하지 않았다면 비어있는 이름을 넣어
		}
		//실행이 잘되는지 보기위해 출력코드 적음
		System.out.println(dto.getId());
		System.out.println(dto.getName());
		System.out.println(dto.getImgName());
		fm.saveData(dto);//넘기기.db에 저장
	}
	
	public void getShoesImage(Model model) {//오버라이딩
		model.addAttribute("list", fm.getShoesImage());//이미지 꺼내오기
		
	}
}
