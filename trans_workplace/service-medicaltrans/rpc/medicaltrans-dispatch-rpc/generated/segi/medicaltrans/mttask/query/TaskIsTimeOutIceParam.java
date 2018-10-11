// **********************************************************************
//
// Copyright (c) 2003-2016 ZeroC, Inc. All rights reserved.
//
// This copy of Ice is licensed to you under the terms described in the
// ICE_LICENSE file included in this distribution.
//
// **********************************************************************
//
// Ice version 3.6.3
//
// <auto-generated>
//
// Generated from file `mt_task_query.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package segi.medicaltrans.mttask.query;

public class TaskIsTimeOutIceParam implements java.lang.Cloneable, java.io.Serializable
{
    public String code;

    public String
    getCode()
    {
        return code;
    }

    public void
    setCode(String _code)
    {
        code = _code;
    }

    public String message;

    public String
    getMessage()
    {
        return message;
    }

    public void
    setMessage(String _message)
    {
        message = _message;
    }

    public String taskId;

    public String
    getTaskId()
    {
        return taskId;
    }

    public void
    setTaskId(String _taskId)
    {
        taskId = _taskId;
    }

    public String isTimeOut;

    public String
    getIsTimeOut()
    {
        return isTimeOut;
    }

    public void
    setIsTimeOut(String _isTimeOut)
    {
        isTimeOut = _isTimeOut;
    }

    public TaskIsTimeOutIceParam()
    {
        code = "";
        message = "";
        taskId = "";
        isTimeOut = "";
    }

    public TaskIsTimeOutIceParam(String code, String message, String taskId, String isTimeOut)
    {
        this.code = code;
        this.message = message;
        this.taskId = taskId;
        this.isTimeOut = isTimeOut;
    }

    public boolean
    equals(java.lang.Object rhs)
    {
        if(this == rhs)
        {
            return true;
        }
        TaskIsTimeOutIceParam _r = null;
        if(rhs instanceof TaskIsTimeOutIceParam)
        {
            _r = (TaskIsTimeOutIceParam)rhs;
        }

        if(_r != null)
        {
            if(code != _r.code)
            {
                if(code == null || _r.code == null || !code.equals(_r.code))
                {
                    return false;
                }
            }
            if(message != _r.message)
            {
                if(message == null || _r.message == null || !message.equals(_r.message))
                {
                    return false;
                }
            }
            if(taskId != _r.taskId)
            {
                if(taskId == null || _r.taskId == null || !taskId.equals(_r.taskId))
                {
                    return false;
                }
            }
            if(isTimeOut != _r.isTimeOut)
            {
                if(isTimeOut == null || _r.isTimeOut == null || !isTimeOut.equals(_r.isTimeOut))
                {
                    return false;
                }
            }

            return true;
        }

        return false;
    }

    public int
    hashCode()
    {
        int __h = 5381;
        __h = IceInternal.HashUtil.hashAdd(__h, "::segi::medicaltrans::mttask::query::TaskIsTimeOutIceParam");
        __h = IceInternal.HashUtil.hashAdd(__h, code);
        __h = IceInternal.HashUtil.hashAdd(__h, message);
        __h = IceInternal.HashUtil.hashAdd(__h, taskId);
        __h = IceInternal.HashUtil.hashAdd(__h, isTimeOut);
        return __h;
    }

    public TaskIsTimeOutIceParam
    clone()
    {
        TaskIsTimeOutIceParam c = null;
        try
        {
            c = (TaskIsTimeOutIceParam)super.clone();
        }
        catch(CloneNotSupportedException ex)
        {
            assert false; // impossible
        }
        return c;
    }

    public void
    __write(IceInternal.BasicStream __os)
    {
        __os.writeString(code);
        __os.writeString(message);
        __os.writeString(taskId);
        __os.writeString(isTimeOut);
    }

    public void
    __read(IceInternal.BasicStream __is)
    {
        code = __is.readString();
        message = __is.readString();
        taskId = __is.readString();
        isTimeOut = __is.readString();
    }

    static public void
    __write(IceInternal.BasicStream __os, TaskIsTimeOutIceParam __v)
    {
        if(__v == null)
        {
            __nullMarshalValue.__write(__os);
        }
        else
        {
            __v.__write(__os);
        }
    }

    static public TaskIsTimeOutIceParam
    __read(IceInternal.BasicStream __is, TaskIsTimeOutIceParam __v)
    {
        if(__v == null)
        {
             __v = new TaskIsTimeOutIceParam();
        }
        __v.__read(__is);
        return __v;
    }
    
    private static final TaskIsTimeOutIceParam __nullMarshalValue = new TaskIsTimeOutIceParam();

    public static final long serialVersionUID = -88016649L;
}
