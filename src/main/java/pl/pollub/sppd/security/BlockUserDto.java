package pl.pollub.sppd.security;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.pollub.sppd.model.Person;
import pl.pollub.sppd.model.accountStatus.AccountStatus;

@Getter
@Setter
@NoArgsConstructor
public class BlockUserDto {

    Long Id;
    String userName;
    AccountStatus accountStatus;

    public static BlockUserDto personToBlockUserDto(Person person){
        BlockUserDto blockUserDto = new BlockUserDto();
        blockUserDto.setId(person.getId());
        blockUserDto.setUserName(person.getName());
        blockUserDto.setAccountStatus(person.getAccountStatus());
        return blockUserDto;
    }

    public static Person BlockUserDtoToPerson(BlockUserDto blockUserDto){
        Person person = new Person();
        person.setId(blockUserDto.getId());
        person.setAccountStatus(blockUserDto.getAccountStatus());
        return person;
    }
}
