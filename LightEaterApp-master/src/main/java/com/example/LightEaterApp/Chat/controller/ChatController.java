package com.example.LightEaterApp.Chat.controller;

import com.example.LightEaterApp.Chat.dto.ChatDTO;
import com.example.LightEaterApp.Chat.dto.GetResponseDTO;
import com.example.LightEaterApp.Chat.dto.PostResponseDTO;
import com.example.LightEaterApp.Chat.dto.PostResponseTestDTO;
import com.example.LightEaterApp.Chat.model.ChatEntity;
import com.example.LightEaterApp.Chat.service.ChatService;
import com.example.LightEaterApp.Chat.service.FlaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("chatAnalysis")
public class ChatController {
    @Autowired
    private ChatService service;
    @Autowired
    private FlaskService flaskService;
    /*
    @PostMapping
    public ResponseEntity<?> createChat(
           //@AuthenticationPrincipal String userId,
            @RequestBody ChatDTO dto) {
        try {
            String temporaryUserId = "userId";             //이부분은 로그인 구현시 userId 로 대체되어 들어갈 부분 ->로그인 구현시 삭제


            ChatEntity entity = ChatDTO.toEntity(dto);

            entity.setChatId(null);

            entity.setUserId(temporaryUserId);

            entity.setResultNum(100);

            List<ChatEntity> entities = service.create(entity);

            List<ChatDTO> dtos = entities.stream()
                    .map(ChatDTO::new)
                    .collect(Collectors.toList());

            PostResponseDTO<ChatDTO> response = PostResponseDTO.<ChatDTO>builder()
                    .data(dtos).build();

            return ResponseEntity.ok().body(response);
        } catch(Exception e) {                                      //예외 있는 경우 dto 대신 error 메세지 넣어 리턴
            String error = e.getMessage();
            PostResponseDTO<ChatDTO> response = PostResponseDTO.<ChatDTO>builder()
                    .error(error).build();

            return ResponseEntity.badRequest().body(response);

        }
    }
*/
    @PostMapping("/ByDataList")
    public ResponseEntity<?> createChatTest(
            //@AuthenticationPrincipal String userId,
            @RequestBody ChatDTO dto) {
        try {
            //responsebody 바꾸는중
            String temporaryUserId = "userId";             //이부분은 로그인 구현시 userId 로 대체되어 들어갈 부분 ->로그인 구현시 삭제


            ChatEntity entity = ChatDTO.toEntity(dto);

            entity.setChatId(null);

            entity.setUserId(temporaryUserId);

            entity.setResultNum(100);

            List<ChatEntity> entities = service.createTest(entity);

            List<ChatDTO> dtos = entities.stream()
                    .map(ChatDTO::new)
                    .collect(Collectors.toList());

            PostResponseDTO<ChatDTO> response = PostResponseDTO.<ChatDTO>builder()
                    .data(dtos).build();

            return ResponseEntity.ok().body(response);
        } catch(Exception e) {                                      //예외 있는 경우 dto 대신 error 메세지 넣어 리턴
            String error = e.getMessage();
            PostResponseDTO<ChatDTO> response = PostResponseDTO.<ChatDTO>builder()
                    .error(error).build();

            return ResponseEntity.badRequest().body(response);

        }
    }
    //list빼고 변수만으로 테스트중
    @PostMapping("/justVariable")
    public ResponseEntity<?> createChatTestByVariable(
            //@AuthenticationPrincipal String userId,
            @RequestBody ChatDTO dto) {
        try {
            /*
    private String error;
    private String userId;
    private String chatId;
    private int resultNum;
             */
            //responsebody 바꾸는중
            String temporaryUserId = "userId";             //이부분은 로그인 구현시 userId 로 대체되어 들어갈 부분 ->로그인 구현시 삭제


            ChatEntity entity = ChatDTO.toEntity(dto);

            entity.setChatId(null);

            entity.setUserId(temporaryUserId);

            int resultnum = flaskService.sendChatWords(entity.getChatWords()).block();

            entity.setResultNum(resultnum);

            List<ChatEntity> entities = service.createTest(entity);         //프론트에서 보내주면 전체 db말고 해당chatId entity만 리턴
/*
            List<ChatDTO> dtos = entities.stream()
                    .map(ChatDTO::new)
                    .collect(Collectors.toList());


 */
            PostResponseTestDTO response = PostResponseTestDTO.<ChatDTO>builder()
                    .userId(entity.getUserId())
                    .chatId(entity.getChatId())
                    .resultNum(entity.getResultNum())
                    .chatWords(entity.getChatWords())
                    .build();

            return ResponseEntity.ok().body(response);
        } catch(Exception e) {                                      //예외 있는 경우 dto 대신 error 메세지 넣어 리턴
            String error = e.getMessage();
            PostResponseDTO<ChatDTO> response = PostResponseDTO.<ChatDTO>builder()
                    .error(error).build();

            return ResponseEntity.badRequest().body(response);

        }
    }
    //@PostMapping("AI")


    @GetMapping
    public ResponseEntity<?> retrieveTodoList(@RequestBody ChatDTO dto) {   //저장된 리스트 반환
        String temporaryUserId ="userId";

        ChatEntity entity = ChatDTO.toEntity(dto);

        entity.setUserId((temporaryUserId));
        entity.setResultNum(60);


        List<ChatEntity> entities = service.retrieve(entity);

        List<ChatDTO> dtos = entities.stream().map(ChatDTO::new).collect(Collectors.toList());
        //List<ChatDTO> responsedto = dtos.stream().map(ChatDTO::toArray).collect(toList());

        GetResponseDTO response = GetResponseDTO.<ChatDTO>builder()
                .userId(dtos.toString())
                .chatId(entity.getChatId())
                .chatWords(dtos.toString())
                .resultNum(100)
                .build();


        return ResponseEntity.ok().body(response);
    }

}
