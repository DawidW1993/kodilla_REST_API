package com.crud.tasks.domain;

import com.crud.tasks.domain.card.TrelloCard;
import com.crud.tasks.domain.card.TrelloCardDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
public class TrelloMapperTest {
    TrelloMapper trelloMapper = new TrelloMapper() ;

    TrelloCard trelloCard = new TrelloCard("test","jUnit4 test", "10","1");
    TrelloCardDto trelloCardDto = new TrelloCardDto("test","jUnit4 test", "11","2");
    List<TrelloBoard> trelloBoards = new ArrayList<>();
    List<TrelloBoardDto> listTrelloBoardDto = new ArrayList<>();
    List<TrelloList> trelloLists = new ArrayList<>();
    List<TrelloListDto> trelloListDto = new ArrayList<>();

    @Test
    public void mapToBoard() {
        //Given
        List<TrelloListDto> testList = new ArrayList<>();
        TrelloBoardDto trelloBoardDto = new TrelloBoardDto("10","test",testList);
        listTrelloBoardDto.add(trelloBoardDto);
        //When
        List<TrelloBoard> testTrelloBoards = trelloMapper.mapToBoard(listTrelloBoardDto);
        //Then
        TrelloBoard testBoard = testTrelloBoards.get(0);
        assertEquals("10",testBoard.getId());
        assertEquals("test",testBoard.getName());
        assertTrue(testBoard.getLists().isEmpty());
    }

    @Test
    public void mapToBoardsDto() {
        //Given
        List<TrelloList> testList = new ArrayList<>();
        TrelloBoard testBoard = new TrelloBoard("10","test",testList);
        trelloBoards.add(testBoard);
        //When
        List<TrelloBoardDto> testBoardsDto = trelloMapper.mapToBoardsDto(trelloBoards);
        //Then
        TrelloBoardDto testBoardDto = testBoardsDto.get(0);
        assertEquals("10",testBoardDto.getId());
        assertEquals("test",testBoardDto.getName());
        assertTrue(testBoardDto.getLists().isEmpty());
    }

    @Test
    public void mapToList() {
        //Given
        TrelloListDto testListDto = new TrelloListDto("10","test",true);
        trelloListDto.add(testListDto);
        //When
        List<TrelloList> testList = trelloMapper.mapToList(trelloListDto);
        TrelloList trelloList = testList.get(0);
        //Then
        assertEquals("10",trelloList.getId());
        assertEquals("test",trelloList.getName());
        assertTrue(trelloList.isClosed());
    }

    @Test
    public void mapToListDto() {
        //Given
        TrelloList testList = new TrelloList("10","test",true);
        trelloLists.add(testList);
        //When
        List<TrelloListDto> trelloListDtos = trelloMapper.mapToListDto(trelloLists);
        TrelloListDto testDto = trelloListDtos.get(0);
        //Then
        assertEquals("10",testDto.getId());
        assertEquals("test",testDto.getName());
        assertTrue(testDto.isClosed());
    }

    @Test
    public void mapToCard() {
        //When
        TrelloCard trelloCardTest = trelloMapper.mapToCard(trelloCardDto);
        //Then
        assertEquals("test",trelloCardTest.getName());
        assertEquals("jUnit4 test",trelloCardTest.getDescription());
        assertEquals("11",trelloCardTest.getPos());
        assertEquals("2",trelloCardTest.getListId());
    }

    @Test
    public void mapToCardDto() {
        //When
        TrelloCardDto trelloCardDtoTest = trelloMapper.mapToCardDto(trelloCard);
        //Then
        assertEquals("test",trelloCardDtoTest.getName());
        assertEquals("jUnit4 test",trelloCardDtoTest.getDescription());
        assertEquals("10",trelloCardDtoTest.getPos());
        assertEquals("1",trelloCardDtoTest.getListId());
    }

}
