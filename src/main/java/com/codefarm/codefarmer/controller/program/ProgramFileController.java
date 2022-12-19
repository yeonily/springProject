package com.codefarm.codefarmer.controller.program;

import com.codefarm.codefarmer.domain.program.ProgramFileDTO;
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
@RequestMapping("/file/*")
@Slf4j
public class ProgramFileController {

    @PostMapping("/upload")
    public List<ProgramFileDTO> upload(List<MultipartFile> upload) throws IOException {

        String rootPath = "C:/upload";
        String uploadFileName = null;
        List<ProgramFileDTO> files = new ArrayList<>();

        File uploadPath = new File(rootPath, createDirectoryByNow());
        if(!uploadPath.exists()){
            uploadPath.mkdirs();
        }

        for(MultipartFile multipartFile : upload){
            log.info("멀티파트는" + multipartFile.toString());
            ProgramFileDTO ProgramFileDTO = new ProgramFileDTO();
            UUID uuid = UUID.randomUUID();
            String fileName = multipartFile.getOriginalFilename();
            uploadFileName = uuid.toString() + "_" + fileName;
            ProgramFileDTO.setFileName(fileName);
            ProgramFileDTO.setFileUuid(uuid.toString());
            ProgramFileDTO.setFileUploadPath(createDirectoryByNow());
            ProgramFileDTO.setFileSize(multipartFile.getSize());

            File saveFile = new File(uploadPath, uploadFileName);
            multipartFile.transferTo(saveFile);

            if(checkImageType(saveFile)){
                FileOutputStream thumbnail = new FileOutputStream(new File(uploadPath, "s_" + uploadFileName));
                Thumbnailator.createThumbnail(multipartFile.getInputStream(), thumbnail, 200, 200);
                thumbnail.close();
                ProgramFileDTO.setFileImageCheck(true);
            }
            files.add(ProgramFileDTO);
        }
        return files;
    }

    @GetMapping("/display")
    public byte[] display(String fileName) throws IOException {
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