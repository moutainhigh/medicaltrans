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
// Generated from file `task_loop.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package segi.medicaltrans.base.taskloop;

public class TaskLoopPageParam implements java.lang.Cloneable, java.io.Serializable
{
    public String organId;

    public String
    getOrganId()
    {
        return organId;
    }

    public void
    setOrganId(String _organId)
    {
        organId = _organId;
    }

    public String taskName;

    public String
    getTaskName()
    {
        return taskName;
    }

    public void
    setTaskName(String _taskName)
    {
        taskName = _taskName;
    }

    public String transTypeParentCode;

    public String
    getTransTypeParentCode()
    {
        return transTypeParentCode;
    }

    public void
    setTransTypeParentCode(String _transTypeParentCode)
    {
        transTypeParentCode = _transTypeParentCode;
    }

    public String status;

    public String
    getStatus()
    {
        return status;
    }

    public void
    setStatus(String _status)
    {
        status = _status;
    }

    public String pageNo;

    public String
    getPageNo()
    {
        return pageNo;
    }

    public void
    setPageNo(String _pageNo)
    {
        pageNo = _pageNo;
    }

    public String pageLength;

    public String
    getPageLength()
    {
        return pageLength;
    }

    public void
    setPageLength(String _pageLength)
    {
        pageLength = _pageLength;
    }

    public TaskLoopPageParam()
    {
        organId = "";
        taskName = "";
        transTypeParentCode = "";
        status = "";
        pageNo = "";
        pageLength = "";
    }

    public TaskLoopPageParam(String organId, String taskName, String transTypeParentCode, String status, String pageNo, String pageLength)
    {
        this.organId = organId;
        this.taskName = taskName;
        this.transTypeParentCode = transTypeParentCode;
        this.status = status;
        this.pageNo = pageNo;
        this.pageLength = pageLength;
    }

    public boolean
    equals(java.lang.Object rhs)
    {
        if(this == rhs)
        {
            return true;
        }
        TaskLoopPageParam _r = null;
        if(rhs instanceof TaskLoopPageParam)
        {
            _r = (TaskLoopPageParam)rhs;
        }

        if(_r != null)
        {
            if(organId != _r.organId)
            {
                if(organId == null || _r.organId == null || !organId.equals(_r.organId))
                {
                    return false;
                }
            }
            if(taskName != _r.taskName)
            {
                if(taskName == null || _r.taskName == null || !taskName.equals(_r.taskName))
                {
                    return false;
                }
            }
            if(transTypeParentCode != _r.transTypeParentCode)
            {
                if(transTypeParentCode == null || _r.transTypeParentCode == null || !transTypeParentCode.equals(_r.transTypeParentCode))
                {
                    return false;
                }
            }
            if(status != _r.status)
            {
                if(status == null || _r.status == null || !status.equals(_r.status))
                {
                    return false;
                }
            }
            if(pageNo != _r.pageNo)
            {
                if(pageNo == null || _r.pageNo == null || !pageNo.equals(_r.pageNo))
                {
                    return false;
                }
            }
            if(pageLength != _r.pageLength)
            {
                if(pageLength == null || _r.pageLength == null || !pageLength.equals(_r.pageLength))
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
        __h = IceInternal.HashUtil.hashAdd(__h, "::segi::medicaltrans::base::taskloop::TaskLoopPageParam");
        __h = IceInternal.HashUtil.hashAdd(__h, organId);
        __h = IceInternal.HashUtil.hashAdd(__h, taskName);
        __h = IceInternal.HashUtil.hashAdd(__h, transTypeParentCode);
        __h = IceInternal.HashUtil.hashAdd(__h, status);
        __h = IceInternal.HashUtil.hashAdd(__h, pageNo);
        __h = IceInternal.HashUtil.hashAdd(__h, pageLength);
        return __h;
    }

    public TaskLoopPageParam
    clone()
    {
        TaskLoopPageParam c = null;
        try
        {
            c = (TaskLoopPageParam)super.clone();
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
        __os.writeString(organId);
        __os.writeString(taskName);
        __os.writeString(transTypeParentCode);
        __os.writeString(status);
        __os.writeString(pageNo);
        __os.writeString(pageLength);
    }

    public void
    __read(IceInternal.BasicStream __is)
    {
        organId = __is.readString();
        taskName = __is.readString();
        transTypeParentCode = __is.readString();
        status = __is.readString();
        pageNo = __is.readString();
        pageLength = __is.readString();
    }

    static public void
    __write(IceInternal.BasicStream __os, TaskLoopPageParam __v)
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

    static public TaskLoopPageParam
    __read(IceInternal.BasicStream __is, TaskLoopPageParam __v)
    {
        if(__v == null)
        {
             __v = new TaskLoopPageParam();
        }
        __v.__read(__is);
        return __v;
    }
    
    private static final TaskLoopPageParam __nullMarshalValue = new TaskLoopPageParam();

    public static final long serialVersionUID = 540773955L;
}
