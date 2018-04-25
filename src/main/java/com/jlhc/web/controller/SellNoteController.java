package com.jlhc.web.controller;

import com.jlhc.common.dto.Msg;
import com.jlhc.common.utils.ResultUtil;
import com.jlhc.oa.dto.user.User;
import com.jlhc.sell.dto.Note;
import com.jlhc.sell.dto.NoteForPut;
import com.jlhc.sell.service.NoteService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@RestController
@RequestMapping("/note")
public class SellNoteController extends BaseController {
    @Autowired
    NoteService noteService;

    /**
     * 添加一条记录
     *
     * @param httpSession
     * @return
     */
    @PostMapping("postNote")
    @RequiresPermissions("note:postNote")
    public Msg postNote(@RequestParam @NotNull @Size(min = 32,max = 32,message = "代理主键为32位全球唯一码")String taskId,
                        @RequestParam @NotNull @Size(min =1,max = 255,message = "内容不能为空,且内容长度不能超过255个字") String noteContent,
                        HttpSession httpSession){
        try{
            Integer resultNum = 0;
            User user = (User)httpSession.getAttribute("user");
            resultNum += noteService.createPost(taskId,noteContent,user.getUserId());
            if ( 0 == resultNum ){
                return ResultUtil.operationFailed("添加了"+resultNum+"条工作记录");
            }
            return ResultUtil.addSuccess(resultNum);
        }catch(Exception e){
            return errorResultOperation(e);
        }
    }

    /**
     * 查询某一任务的工作记录
     *
     * @param taskId
     * @return
     */
    @GetMapping("getNotesByTaskId/{taskId}")
    @RequiresPermissions("note:getNotesByTaskId")
    public Msg getNotesByTaskId(@PathVariable @NotNull @Size(min = 32,max = 32,message = "代理主键为32位全球唯一码")String taskId){
        try{
            List<Note> notes = noteService.queryNotesByTaskId(taskId);
            if (null == notes || 0 == notes.size()){
                return ResultUtil.selectFailed();
            }
            return ResultUtil.selectSuccess(notes);
        }catch(Exception e){
            return errorResultOperation(e);
        }
    }

    /**
     * 修改本人工作记录
     *
     * @param note
     * @param httpSession
     * @return
     */
    @PutMapping("putNoteOfMine")
    @RequiresPermissions("note:putNoteOfMine")
    public Msg putNoteOfMine(@RequestBody @Valid NoteForPut note,HttpSession httpSession){
        try{
            Integer resultNum = noteService.reworkNote(note,httpSession);
            if( 0 >= resultNum){
                return ResultUtil.operationFailed();
            }
            return ResultUtil.updateSuccess(resultNum);
        }catch(Exception e){
            return errorResultOperation(e);
        }
    }

    /**
     * 修改某一条工作记录
     *
     * @param note
     * @return
     */
    @PutMapping("putNoteById")
    @RequiresPermissions("note:putNoteById")
    public Msg putNoteById(@RequestBody @Valid Note note){
        try{
            Integer resultNum = noteService.reworkNote(note);
            if( 0 >= resultNum){
                return ResultUtil.operationFailed();
            }
            return ResultUtil.updateSuccess(resultNum);
        }catch(Exception e){
            return errorResultOperation(e);
        }
    }

    /**
     * 删除某条工作记录
     *
     * @param noteId
     * @return
     */
    @DeleteMapping("deleteNote/{noteId}")
    @RequiresPermissions("note:deleteNote")
    public Msg deleteNote(@PathVariable @NotNull @Size(min = 32,max = 32,message = "代理主键为32位全球唯一码") String noteId){
        try{
            Integer resultNum = noteService.dropNote(noteId);
            if( 0 >= resultNum){
                return ResultUtil.operationFailed();
            }
            return ResultUtil.deleteSuccess(resultNum);
        }catch(Exception e){
            return errorResultOperation(e);
        }
    }

}
