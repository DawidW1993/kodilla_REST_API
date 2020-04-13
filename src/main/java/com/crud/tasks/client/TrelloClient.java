package com.crud.tasks.client;

import com.crud.tasks.domain.TrelloBoardDto;
import lombok.Getter;
import lombok.Setter;
import org.graalvm.compiler.nodes.calc.IntegerDivRemNode;
import org.hibernate.cfg.CollectionSecondPass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.lang.reflect.Array;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
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

        TrelloBoardDto[] boardsResponse = restTemplate.getForObject(getURL(),TrelloBoardDto[].class);

        return createListWithOptionals(boardsResponse);
    }

    private URI getURL(){
        URI url = UriComponentsBuilder.fromHttpUrl(trelloApiEndpoint + "/members/" +trelloUsername +"/boards")
                .queryParam("key", trelloAppKey)
                .queryParam("token", trelloToken)
                .queryParam("fields", "name,id").build().encode().toUri();

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
