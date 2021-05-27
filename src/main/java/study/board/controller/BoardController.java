package study.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import study.board.dto.BoardDto;
import study.board.service.BoardService;
import study.board.util.MD5Generator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
public class BoardController {

    @Autowired
    ResourceLoader resourceLoader;

    private BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/")
    public String list(Model model) {
        List<BoardDto> boardDtoList = boardService.getBoardList();
        model.addAttribute("postList", boardDtoList);
        return "board/list.html";
    }

    @GetMapping("/post")
    public String post() {
        return "board/post.html";
    }

    @PostMapping("/post")
    public String write(@RequestParam("file") MultipartFile files, BoardDto boardDto) {
        try {
            String origFilename = files.getOriginalFilename();
            String filename = new MD5Generator(origFilename).toString();
            // 새로만든거 확장자 포함해서 파일을 확인하기 위한 방법...
            String extendsFileName = filename+".png";
            /* 실행되는 위치의 'files' 폴더에 파일이 저장됩니다. */
            String savePath = System.getProperty("user.dir") + "\\files";
            /* 파일이 저장되는 폴더가 없으면 폴더를 생성합니다. */
            if (!new java.io.File(savePath).exists()) {
                try{
                    new java.io.File(savePath).mkdir();
                }
                catch(Exception e){
                    e.getStackTrace();
                }
            }
//            String filePath = savePath + "\\" + filename+".png";
            String filePath = savePath + "\\" + extendsFileName;
            files.transferTo(new java.io.File(filePath));

//            FileDto fileDto = new FileDto();
//            fileDto.setOrigFilename(origFilename);
////            fileDto.setFilename(filename);
//            fileDto.setFilename(extendsFileName);
//            fileDto.setFilePath(filePath);
//
//            Long fileId = fileService.saveFile(fileDto);
//            boardDto.setFileId(fileId);


            boardDto.setOrigFilename(origFilename);
//            fileDto.setFilename(filename);
            boardDto.setFilename(extendsFileName);
            boardDto.setFilepath(filePath);

//            Long fileId = fileService.saveFile(fileDto);
//            boardDto.setFileId(fileId);
            boardService.savePost(boardDto);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return "redirect:/";
    }

    @GetMapping("/post/{id}")
    public String detail(@PathVariable("id") Long id, Model model) {
        BoardDto boardDto = boardService.getPost(id);
        model.addAttribute("post", boardDto);
        return "board/detail.html";
    }

    @GetMapping("/post/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        BoardDto boardDto = boardService.getPost(id);
        model.addAttribute("post", boardDto);
        return "board/edit.html";
    }

    @PutMapping("/post/edit/{id}")
    public String update(BoardDto boardDto) {
        boardService.savePost(boardDto);
        return "redirect:/";
    }

    @DeleteMapping("/post/{id}")
    public String delete(@PathVariable("id") Long id) {
        boardService.deletePost(id);
        return "redirect:/";
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> fileDownload(@PathVariable("id") Long id) throws IOException {
//        FileDto fileDto = fileService.getFile(fileId);
        BoardDto boardDto = boardService.getPost(id);
        Path path = Paths.get(boardDto.getFilepath());
        Resource resource = new InputStreamResource(Files.newInputStream(path));
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + boardDto.getOrigFilename() + "\"")
                .body(resource);
    }
}
