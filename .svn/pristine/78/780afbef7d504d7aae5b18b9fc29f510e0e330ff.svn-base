package com.jlhc.sell.service;

import com.jlhc.common.exception.NullEntityInDatabaseException;
import com.jlhc.sell.dto.Note;
import com.jlhc.sell.dto.NoteForPut;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface NoteService {
    Integer createPost(String taskId, String noteContent, Integer userId) throws NullEntityInDatabaseException;

    List<Note> queryNotesByTaskId(String taskId) throws NullEntityInDatabaseException;

    Integer reworkNote(NoteForPut note, HttpSession httpSession) throws NullEntityInDatabaseException, NullPointerException;

    Integer reworkNote(Note note) throws NullEntityInDatabaseException;

    Integer dropNote(String noteId);
}
