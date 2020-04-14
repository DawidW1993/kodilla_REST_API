package com.crud.tasks.client;

import com.crud.tasks.domain.card.CreatedTrelloCard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.card.TrelloCardDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
@Setter
@Component
public class TrelloClient {

    @Value("${trello.api.endpoint.prod}")
    private String trelloApiEndpoint;

    @Value("${trello.app.key}")
    private String trelloAppKey;

    @Value("${trello.app.token}")
    private String trelloToken;

    @Value("${trello.api.username}")
    private String trelloUsername;

    @Autowired
    private RestTemplate restTemplate;

    public List<TrelloBoardDto> getTrelloBoard(){

        TrelloBoardDto[] boardsResponse = restTemplate.getForObject(getTrelloBoardURL(),TrelloBoardDto[].class);

        return createListWithOptionals(boardsResponse);
    }

    public CreatedTrelloCard createNewCard(TrelloCardDto trelloCardDto){

        return restTemplate.postForObject(createNewCardURL(trelloCardDto), null, CreatedTrelloCard.class);
    }

    private URI getTrelloBoardURL(){
        URI url = UriComponentsBuilder.fromHttpUrl(trelloApiEndpoint + "/members/" +trelloUsername +"/boards")
                .queryParam("key", trelloAppKey)
                .queryParam("token", trelloToken)
                .queryParam("fields", "name,id")
                .queryParam("lists", "all")
                .build().encode().toUri();

        return url;
    }

    private URI createNewCardURL(TrelloCardDto trelloCardDto){
        URI url = UriComponentsBuilder.fromHttpUrl(trelloApiEndpoint + "/cards")
                .queryParam("key", trelloAppKey)
                .queryParam("token", trelloToken)
                .queryParam("name", trelloCardDto.getName())
                .queryParam("desc", trelloCardDto.getDescription())
                .queryParam("pos", trelloCardDto.getPos())
                .queryParam("idList", trelloCardDto.getListId())
                .queryParam("badges", trelloCardDto.getCardBadges())
                .build().encode().toUri();

        return url;
    }

    private List<TrelloBoardDto> createListWithOptionals(TrelloBoardDto[] boardDtos){

        List<Optional<TrelloBoardDto>> optionals = Arrays.asList(boardDtos).stream()
                .map(Optional::ofNullable)
                .collect(Collectors.toList());

        return optionals.stream()
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }
}
