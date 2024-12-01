package com.atest.aboard.mapper;

import com.atest.aboard.domain.Board;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BoardMapper {

    @Select("SELECT * FROM board ORDER BY bno DESC")
    List<Board> getAllBoards();

    @Insert("INSERT INTO board (title, content, writer, regdate) VALUES (#{title}, #{content}, #{writer}, NOW())")
    @Options(useGeneratedKeys = true, keyProperty = "bno")
    int insertBoard(Board board);

    @Select("SELECT * FROM board WHERE bno = #{bno}")
    Board getBoardById(int bno);

    @Update("UPDATE board SET title = #{title}, content = #{content}, updatedate = NOW() WHERE bno = #{bno}")
    int updateBoard(Board board);

    @Delete("DELETE FROM board WHERE bno = #{bno}")
    int deleteBoard(int bno);
}
