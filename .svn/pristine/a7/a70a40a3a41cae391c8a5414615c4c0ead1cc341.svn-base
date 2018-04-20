package com.jlhc.sell.service.impl;

import com.jlhc.common.exception.NotVertifyException;
import com.jlhc.common.exception.NullEntityInDatabaseException;
import com.jlhc.common.utils.RandomStringUtil;
import com.jlhc.oa.dao.UserMapper;
import com.jlhc.oa.dto.user.User;
import com.jlhc.sell.dao.NoteMapper;
import com.jlhc.sell.dao.TaskMapper;
import com.jlhc.sell.dto.Note;
import com.jlhc.sell.dto.NoteForPut;
import com.jlhc.sell.dto.Task;
import com.jlhc.sell.dto.example.NoteExample;
import com.jlhc.sell.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class NoteServiceImpl implements NoteService {
    @Autowired
    NoteMapper noteMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    TaskMapper taskMapper;
    public static NoteExample noteExample = new NoteExample();
    @Override
    public Integer createPost(String taskId, String noteContent, Integer userId) throws NullEntityInDatabaseException {
        User user = userMapper.selectByPrimaryKey(userId);
        if (null == user){
            throw new NullEntityInDatabaseException();
        }
        Task task = taskMapper.selectByPrimaryKey(taskId);
        if (null == user){
            throw new NullEntityInDatabaseException();
        }
        Note note = new Note();
        note.setNoteId(RandomStringUtil.getRandomCode(32,7));
        note.setNoteContent(noteContent);
        note.setNoteCreatedTime(new Date());
        note.setTaskId(taskId);
        note.setUserId(userId);
        Integer resultNum = noteMapper.insertSelective(note);
        return resultNum;
    }

    @Override
    public List<Note> queryNotesByTaskId(String taskId) throws NullEntityInDatabaseException {
        Task task = taskMapper.selectByPrimaryKey(taskId);
        if (null == task){
            throw new NullEntityInDatabaseException();
        }
        noteExample.clear();
        noteExample.createCriteria()
                .andTaskIdEqualTo(taskId);
        List<Note> notes = noteMapper.selectByExample(noteExample);
        return notes;
    }

    /**
     * 仅仅能修改本人的工作记录
     *
     * @param note
     * @param httpSession
     * @return
     * @throws NullEntityInDatabaseException
     * @throws NullPointerException
     */
    @Override
    public Integer reworkNote(NoteForPut note, HttpSession httpSession) throws NullEntityInDatabaseException, NullPointerException {
        if (null==note.getNoteId()){
            throw new NullPointerException();
        }
        Note noteFromDatabase = noteMapper.selectByPrimaryKey(note.getNoteId());
        Task task = taskMapper.selectByPrimaryKey(note.getTaskId());
        User user = (User)httpSession.getAttribute("user");
        if (noteFromDatabase.getUserId()!= user.getUserId()){
            throw new NotVertifyException();//无权操作数据
        }

        if (null == task||null == noteFromDatabase){
            throw new NullEntityInDatabaseException();
        }
        Note noteForInsert = new Note();
        //noteForInsert.setUserId(note.getUserId());
        noteForInsert.setTaskId(note.getTaskId());
        noteForInsert.setNoteCreatedTime(new Date());//修改它的创建时间,为最后记录时间
        noteForInsert.setNoteContent(note.getNoteContent());
        noteForInsert.setNoteId(note.getNoteId());
        int resultNum = noteMapper.updateByPrimaryKeySelective(noteForInsert);
        return resultNum;
    }

    /**
     * 修改特定的工作记录
     *
     * @param note
     * @return
     */
    @Override
    public Integer reworkNote(Note note) throws NullEntityInDatabaseException {
        //非空校验
        if (null==note.getNoteId()){
            throw new NullPointerException();
        }
        Note noteFromDatabase = noteMapper.selectByPrimaryKey(note.getNoteId());
        Task task = taskMapper.selectByPrimaryKey(note.getTaskId());
        User user = userMapper.selectByPrimaryKey(note.getUserId());
        if (null == task||null == noteFromDatabase||null == user){
            throw new NullEntityInDatabaseException();
        }
        Note  noteForInsert = new Note();
        noteForInsert.setNoteId(note.getNoteId());
        noteForInsert.setNoteContent(note.getNoteContent());
        noteForInsert.setNoteCreatedTime(new Date());
        noteForInsert.setTaskId(note.getTaskId());
        noteForInsert.setUserId(note.getUserId());
        int resultNum = noteMapper.updateByPrimaryKeySelective(noteForInsert);
        return resultNum;
    }

    /**
     * 删除工作记录
     *
     * @param noteId
     * @return
     */
    @Override
    public Integer dropNote(String noteId){
        int resultNum = noteMapper.deleteByPrimaryKey(noteId);
        return resultNum;
    }
}
