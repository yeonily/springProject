package com.codefarm.codefarmer.controller.mento;

import com.codefarm.codefarmer.domain.mentor.MentorFileDTO;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/mentofile/*")
@Slf4j
public class MentoFileController {

    @PostMapping("/upload")
    public List<MentorFileDTO> upload(List<MultipartFile> upload) throws IOException {

        String rootPath = "C:/upload";
        String uploadFileName = null;
        List<MentorFileDTO> files = new ArrayList<>();

        File uploadPath = new File(rootPath, createDirectoryByNow());
        if(!uploadPath.exists()){
            uploadPath.mkdirs();
        }

        for(MultipartFile multipartFile : upload){
            log.info("멀티파트는" + multipartFile.toString());
            MentorFileDTO mentorFileDTO = new MentorFileDTO();
            UUID uuid = UUID.randomUUID();
            String fileName = multipartFile.getOriginalFilename();
            uploadFileName = uuid.toString() + "_" + fileName;
            mentorFileDTO.setFileName(fileName);
            mentorFileDTO.setFileUuid(uuid.toString());
            mentorFileDTO.setFileUploadPath(createDirectoryByNow());
            mentorFileDTO.setFileSize(multipartFile.getSize());

            File saveFile = new File(uploadPath, uploadFileName);
            multipartFile.transferTo(saveFile);

            if(checkImageType(saveFile)){
                FileOutputStream thumbnail = new FileOutputStream(new File(uploadPath, "s_" + uploadFileName));
                Thumbnailator.createThumbnail(multipartFile.getInputStream(), thumbnail, 100, 100);
                thumbnail.close();
                mentorFileDTO.setFileImageCheck(true);
            }
            files.add(mentorFileDTO);
        }
        return files;
    }

    @GetMapping("/display")
    public byte[] display(String fileName) throws IOException {
        log.info("display들어옴");
        File file = new File("C:/upload", fileName);
        return FileCopyUtils.copyToByteArray(file);
    }

    @PostMapping("/delete")
    public void delete(String uploadPath, String fileName, boolean fileImageCheck){
        File file = new File("C:/upload", uploadPath + "/" + fileName);
        if(file.exists()){
            file.delete();
        }

        if(fileImageCheck) {
            file = new File("C:/upload", uploadPath + "/s_" + fileName);
            if (file.exists()) {
                file.delete();
            }
        }
    }

    public boolean checkImageType(File file) throws IOException{
        return Files.probeContentType(file.toPath()).startsWith("image");
    }

    public String createDirectoryByNow(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        Date now = new Date();
        return format.format(now);
    }

}
