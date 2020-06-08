package com.crud.tasks.service;

import com.crud.tasks.domain.TrelloBoardDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrelloServiceTest {
    @Mock
    TrelloService trelloService;

    @Test
    public void fetchTrelloBoards() {
        //Given
        TrelloBoardDto trelloBoardDto = new TrelloBoardDto("1", "test", new ArrayList<>());
        List<TrelloBoardDto> trelloBoardDtos = new ArrayList<>();
        trelloBoardDtos.add(trelloBoardDto);

        when(trelloService.fetchTrelloBoards()).thenReturn(trelloBoardDtos);
        //When
        List<TrelloBoardDto> testDtos = trelloService.fetchTrelloBoards();
        TrelloBoardDto testBoardDto = testDtos.get(0);
        //Then
        assertEquals(1,testDtos.size());
        assertEquals("1",testBoardDto.getId());
        assertEquals("test",testBoardDto.getName());
        assertEquals(0,testBoardDto.getLists().size());
    }

}