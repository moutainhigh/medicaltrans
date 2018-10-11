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
// Generated from file `mt_task_manager.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package segi.medicaltrans.mttask.manager;

public class SendTaskIceParam implements java.lang.Cloneable, java.io.Serializable
{
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

    public String resType;

    public String
    getResType()
    {
        return resType;
    }

    public void
    setResType(String _resType)
    {
        resType = _resType;
    }

    public String exeUserIds;

    public String
    getExeUserIds()
    {
        return exeUserIds;
    }

    public void
    setExeUserIds(String _exeUserIds)
    {
        exeUserIds = _exeUserIds;
    }

    public String exeEndUserId;

    public String
    getExeEndUserId()
    {
        return exeEndUserId;
    }

    public void
    setExeEndUserId(String _exeEndUserId)
    {
        exeEndUserId = _exeEndUserId;
    }

    public String serviceGroupIds;

    public String
    getServiceGroupIds()
    {
        return serviceGroupIds;
    }

    public void
    setServiceGroupIds(String _serviceGroupIds)
    {
        serviceGroupIds = _serviceGroupIds;
    }

    public String sendContent;

    public String
    getSendContent()
    {
        return sendContent;
    }

    public void
    setSendContent(String _sendContent)
    {
        sendContent = _sendContent;
    }

    public String exeUserId;

    public String
    getExeUserId()
    {
        return exeUserId;
    }

    public void
    setExeUserId(String _exeUserId)
    {
        exeUserId = _exeUserId;
    }

    public String userId;

    public String
    getUserId()
    {
        return userId;
    }

    public void
    setUserId(String _userId)
    {
        userId = _userId;
    }

    public String userOrganId;

    public String
    getUserOrganId()
    {
        return userOrganId;
    }

    public void
    setUserOrganId(String _userOrganId)
    {
        userOrganId = _userOrganId;
    }

    public String limitMinute;

    public String
    getLimitMinute()
    {
        return limitMinute;
    }

    public void
    setLimitMinute(String _limitMinute)
    {
        limitMinute = _limitMinute;
    }

    public String transPersonCount;

    public String
    getTransPersonCount()
    {
        return transPersonCount;
    }

    public void
    setTransPersonCount(String _transPersonCount)
    {
        transPersonCount = _transPersonCount;
    }

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

    public SendTaskIceParam()
    {
        taskId = "";
        resType = "";
        exeUserIds = "";
        exeEndUserId = "";
        serviceGroupIds = "";
        sendContent = "";
        exeUserId = "";
        userId = "";
        userOrganId = "";
        limitMinute = "";
        transPersonCount = "";
        organId = "";
    }

    public SendTaskIceParam(String taskId, String resType, String exeUserIds, String exeEndUserId, String serviceGroupIds, String sendContent, String exeUserId, String userId, String userOrganId, String limitMinute, String transPersonCount, String organId)
    {
        this.taskId = taskId;
        this.resType = resType;
        this.exeUserIds = exeUserIds;
        this.exeEndUserId = exeEndUserId;
        this.serviceGroupIds = serviceGroupIds;
        this.sendContent = sendContent;
        this.exeUserId = exeUserId;
        this.userId = userId;
        this.userOrganId = userOrganId;
        this.limitMinute = limitMinute;
        this.transPersonCount = transPersonCount;
        this.organId = organId;
    }

    public boolean
    equals(java.lang.Object rhs)
    {
        if(this == rhs)
        {
            return true;
        }
        SendTaskIceParam _r = null;
        if(rhs instanceof SendTaskIceParam)
        {
            _r = (SendTaskIceParam)rhs;
        }

        if(_r != null)
        {
            if(taskId != _r.taskId)
            {
                if(taskId == null || _r.taskId == null || !taskId.equals(_r.taskId))
                {
                    return false;
                }
            }
            if(resType != _r.resType)
            {
                if(resType == null || _r.resType == null || !resType.equals(_r.resType))
                {
                    return false;
                }
            }
            if(exeUserIds != _r.exeUserIds)
            {
                if(exeUserIds == null || _r.exeUserIds == null || !exeUserIds.equals(_r.exeUserIds))
                {
                    return false;
                }
            }
            if(exeEndUserId != _r.exeEndUserId)
            {
                if(exeEndUserId == null || _r.exeEndUserId == null || !exeEndUserId.equals(_r.exeEndUserId))
                {
                    return false;
                }
            }
            if(serviceGroupIds != _r.serviceGroupIds)
            {
                if(serviceGroupIds == null || _r.serviceGroupIds == null || !serviceGroupIds.equals(_r.serviceGroupIds))
                {
                    return false;
                }
            }
            if(sendContent != _r.sendContent)
            {
                if(sendContent == null || _r.sendContent == null || !sendContent.equals(_r.sendContent))
                {
                    return false;
                }
            }
            if(exeUserId != _r.exeUserId)
            {
                if(exeUserId == null || _r.exeUserId == null || !exeUserId.equals(_r.exeUserId))
                {
                    return false;
                }
            }
            if(userId != _r.userId)
            {
                if(userId == null || _r.userId == null || !userId.equals(_r.userId))
                {
                    return false;
                }
            }
            if(userOrganId != _r.userOrganId)
            {
                if(userOrganId == null || _r.userOrganId == null || !userOrganId.equals(_r.userOrganId))
                {
                    return false;
                }
            }
            if(limitMinute != _r.limitMinute)
            {
                if(limitMinute == null || _r.limitMinute == null || !limitMinute.equals(_r.limitMinute))
                {
                    return false;
                }
            }
            if(transPersonCount != _r.transPersonCount)
            {
                if(transPersonCount == null || _r.transPersonCount == null || !transPersonCount.equals(_r.transPersonCount))
                {
                    return false;
                }
            }
            if(organId != _r.organId)
            {
                if(organId == null || _r.organId == null || !organId.equals(_r.organId))
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
        __h = IceInternal.HashUtil.hashAdd(__h, "::segi::medicaltrans::mttask::manager::SendTaskIceParam");
        __h = IceInternal.HashUtil.hashAdd(__h, taskId);
        __h = IceInternal.HashUtil.hashAdd(__h, resType);
        __h = IceInternal.HashUtil.hashAdd(__h, exeUserIds);
        __h = IceInternal.HashUtil.hashAdd(__h, exeEndUserId);
        __h = IceInternal.HashUtil.hashAdd(__h, serviceGroupIds);
        __h = IceInternal.HashUtil.hashAdd(__h, sendContent);
        __h = IceInternal.HashUtil.hashAdd(__h, exeUserId);
        __h = IceInternal.HashUtil.hashAdd(__h, userId);
        __h = IceInternal.HashUtil.hashAdd(__h, userOrganId);
        __h = IceInternal.HashUtil.hashAdd(__h, limitMinute);
        __h = IceInternal.HashUtil.hashAdd(__h, transPersonCount);
        __h = IceInternal.HashUtil.hashAdd(__h, organId);
        return __h;
    }

    public SendTaskIceParam
    clone()
    {
        SendTaskIceParam c = null;
        try
        {
            c = (SendTaskIceParam)super.clone();
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
        __os.writeString(taskId);
        __os.writeString(resType);
        __os.writeString(exeUserIds);
        __os.writeString(exeEndUserId);
        __os.writeString(serviceGroupIds);
        __os.writeString(sendContent);
        __os.writeString(exeUserId);
        __os.writeString(userId);
        __os.writeString(userOrganId);
        __os.writeString(limitMinute);
        __os.writeString(transPersonCount);
        __os.writeString(organId);
    }

    public void
    __read(IceInternal.BasicStream __is)
    {
        taskId = __is.readString();
        resType = __is.readString();
        exeUserIds = __is.readString();
        exeEndUserId = __is.readString();
        serviceGroupIds = __is.readString();
        sendContent = __is.readString();
        exeUserId = __is.readString();
        userId = __is.readString();
        userOrganId = __is.readString();
        limitMinute = __is.readString();
        transPersonCount = __is.readString();
        organId = __is.readString();
    }

    static public void
    __write(IceInternal.BasicStream __os, SendTaskIceParam __v)
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

    static public SendTaskIceParam
    __read(IceInternal.BasicStream __is, SendTaskIceParam __v)
    {
        if(__v == null)
        {
             __v = new SendTaskIceParam();
        }
        __v.__read(__is);
        return __v;
    }
    
    private static final SendTaskIceParam __nullMarshalValue = new SendTaskIceParam();

    public static final long serialVersionUID = -1620097885L;
}
