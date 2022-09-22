package com.springbatch.hellospringbatch.job.player;

import com.springbatch.hellospringbatch.dto.PlayerDto;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

public class PlayerFieldSetMapper implements FieldSetMapper<PlayerDto> {
    @Override
    public PlayerDto mapFieldSet(FieldSet fieldSet) throws BindException {
        PlayerDto dto = new PlayerDto();
        dto.setID(fieldSet.readString(0));
        dto.setLastName(fieldSet.readString(1));
        dto.setFirstName(fieldSet.readString(2));
        dto.setPosition(fieldSet.readString(3));
        dto.setBirthYear(fieldSet.readInt(4));
        dto.setDebutYear(fieldSet.readInt(5));
        return dto;
    }
    /*
      - 읽어 들일때 콘솔에 출력되는 모습.
        파일에 있는 데이터를 읽어들일 수 있다. 직접 반복문을 통해서 읽어들일수도 있겠지만 framework 에서 지원해주는
        기능을 이용하는것이 더 효율적이고, 대용량 처리에 적합하다.

        PlayerDto(ID=AbduKa00, lastName=Abdul-Jabbar, firstName=Karim, position=rb, birthYear=1974, debutYear=1996)
        PlayerDto(ID=AbduRa00, lastName=Abdullah, firstName=Rabih, position=rb, birthYear=1975, debutYear=1999)
        PlayerDto(ID=AberWa00, lastName=Abercrombie, firstName=Walter, position=rb, birthYear=1959, debutYear=1982)
        PlayerDto(ID=AbraDa00, lastName=Abramowicz, firstName=Danny, position=wr, birthYear=1945, debutYear=1967)
        PlayerDto(ID=AdamBo00, lastName=Adams, firstName=Bob, position=te, birthYear=1946, debutYear=1969)
        PlayerDto(ID=AdamCh00, lastName=Adams, firstName=Charlie, position=wr, birthYear=1979, debutYear=2003)
    */
}
