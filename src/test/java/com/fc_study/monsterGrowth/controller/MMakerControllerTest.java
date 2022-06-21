package com.fc_study.monsterGrowth.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fc_study.monsterGrowth.code.StatusCode;
import com.fc_study.monsterGrowth.dto.CreateMonsterDto;
import com.fc_study.monsterGrowth.dto.DetailMonsterDto;
import com.fc_study.monsterGrowth.entity.MonsterEntity;
import com.fc_study.monsterGrowth.repository.MonsterRepository;
import com.fc_study.monsterGrowth.service.MMakerService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.fc_study.monsterGrowth.entity.MonsterEntity.MonsterLevel.ADULT;
import static com.fc_study.monsterGrowth.entity.MonsterEntity.MonsterLevel.BABY;
import static com.fc_study.monsterGrowth.entity.MonsterEntity.MonsterType.FLY;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@WebMvcTest(MMakerController.class)
class MMakerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MMakerService mMakerService;

    private MonsterEntity getDefaultMonster(Long id, String ssn, String name){
        return MonsterEntity.builder()
            .id(id)
            .monsterLevel(BABY)
            .monsterType(FLY)
            .statusCode(StatusCode.HEALTHY)
            .ssn(ssn)
            .name(name)
            .age(3)
            .height(170)
            .weight(73)
            .build();
    }
    private CreateMonsterDto.Request getCreateRequest() {
        return CreateMonsterDto.Request.builder()
                .id(1L)
                .monsterLevel(BABY)
                .monsterType(FLY)
                .statusCode(StatusCode.HEALTHY)
                .ssn("12345612345123")
                .name("BabyMonster")
                .age(3)
                .height(170)
                .weight(73)
                .build();
    }


    /*
          사용하게될 미디어 타입들.
     */
    protected MediaType contentType =
            new MediaType(MediaType.APPLICATION_JSON.getType(),
                    MediaType.APPLICATION_JSON.getSubtype(),
                    StandardCharsets.UTF_8);

    /*
    * 테스트 도중 알게된 것
    * then().should(times()) 의 times의 횟수는 when 에서 가짜 객체를 넣는다면 채킹되지 않고
    * 값을 지니고있는 객체가 들어간다면 채킹된다.
    * */
    @Test
    @DisplayName("Monster get Test")
    void getDetailMonster() throws Exception{
        // given
        MonsterEntity defaultMonster = getDefaultMonster(1L, "12345612345123", "Tiger");
        given(mMakerService.getDetailMonster(any()))
                .willReturn(DetailMonsterDto.fromEntity(getDefaultMonster(1L, "12345612345123", "Tiger")));

        // when
        // then
        mockMvc.perform(
                get("/detail-monster/"+defaultMonster.getSsn())
                        .contentType(contentType)
                        .content(defaultMonster.getSsn()))
                .andExpect(status().isOk())
                .andDo(print());
        then(mMakerService).should(times(1)).getDetailMonster(defaultMonster.getSsn());
    }

    @Test
    @DisplayName("Monster allList Test")
    void getAllList() throws Exception{
        // given
        List<MonsterEntity> monsterList  = new ArrayList<>();
        monsterList.add(getDefaultMonster(1L, "First Monster", "96050312341231"));
        monsterList.add(getDefaultMonster(2L, "Second Monster", "96050312341232"));
        monsterList.add(getDefaultMonster(3L, "Third Monster", "96050312341233"));

        given(mMakerService.getAllDetailMonster())
                .willReturn(monsterList.stream()
                        .map(DetailMonsterDto::fromEntity)
                        .collect(Collectors.toList())
                );

        // when
        // then
        mockMvc.perform(
                get("/all-monster")
                        .contentType(contentType))
                .andExpect(status().isOk())
                .andDo(print());
        then(mMakerService).should(times(1)).getAllDetailMonster();
    }

    @Test
    @DisplayName("Monster Created Test")
    void createMonster() throws Exception {
        // given: 어떠한 데이터가 준비되었을 때, 특정 메소드가 실행되는 경우 실제 Return 을 줄 수 없기
        //        때문에 아래와 같이 가정 사항을 만들어준다.
        MonsterEntity defaultMonster = getDefaultMonster(1L, "96050312341234", "Tiger");
        given(mMakerService.createMonster(getCreateRequest()))
                .willReturn(CreateMonsterDto.TestResponse.fromEntity(defaultMonster));

        // when: 어떠한 함수를 실행하면
        // then: 어떠한 결과가 나와야 한다.
        mockMvc.perform(
                        post("/create-monster")
                                .contentType(contentType)
                                .content(
                                        new ObjectMapper().writeValueAsString(
                                                CreateMonsterDto.TestResponse.fromEntity(defaultMonster)
                                        )))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
        then(mMakerService).should(times(1)).createMonster(getCreateRequest());
    }

    @Test
    @DisplayName("Monster updated Test")
    void updateMonster() throws Exception {
        // given


        // when


        // then

    }

    @Test
    @DisplayName("Monster delete Test")
    void deleteMonster() throws Exception{
        // given


        // when


        // then

    }

}