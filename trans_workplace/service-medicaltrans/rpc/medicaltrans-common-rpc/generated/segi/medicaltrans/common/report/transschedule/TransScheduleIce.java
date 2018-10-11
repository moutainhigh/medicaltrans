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
// Generated from file `mt_trans_schedule.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package segi.medicaltrans.common.report.transschedule;

public class TransScheduleIce implements java.lang.Cloneable, java.io.Serializable
{
    public String groupOrganId;

    public String
    getGroupOrganId()
    {
        return groupOrganId;
    }

    public void
    setGroupOrganId(String _groupOrganId)
    {
        groupOrganId = _groupOrganId;
    }

    public String groupOrganName;

    public String
    getGroupOrganName()
    {
        return groupOrganName;
    }

    public void
    setGroupOrganName(String _groupOrganName)
    {
        groupOrganName = _groupOrganName;
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

    public String organName;

    public String
    getOrganName()
    {
        return organName;
    }

    public void
    setOrganName(String _organName)
    {
        organName = _organName;
    }

    public String startDate;

    public String
    getStartDate()
    {
        return startDate;
    }

    public void
    setStartDate(String _startDate)
    {
        startDate = _startDate;
    }

    public String paramDate;

    public String
    getParamDate()
    {
        return paramDate;
    }

    public void
    setParamDate(String _paramDate)
    {
        paramDate = _paramDate;
    }

    public String runningStatus;

    public String
    getRunningStatus()
    {
        return runningStatus;
    }

    public void
    setRunningStatus(String _runningStatus)
    {
        runningStatus = _runningStatus;
    }

    public String runningStatusName;

    public String
    getRunningStatusName()
    {
        return runningStatusName;
    }

    public void
    setRunningStatusName(String _runningStatusName)
    {
        runningStatusName = _runningStatusName;
    }

    public String createDate;

    public String
    getCreateDate()
    {
        return createDate;
    }

    public void
    setCreateDate(String _createDate)
    {
        createDate = _createDate;
    }

    public String updateDate;

    public String
    getUpdateDate()
    {
        return updateDate;
    }

    public void
    setUpdateDate(String _updateDate)
    {
        updateDate = _updateDate;
    }

    public String excDate;

    public String
    getExcDate()
    {
        return excDate;
    }

    public void
    setExcDate(String _excDate)
    {
        excDate = _excDate;
    }

    public String lastExcDate;

    public String
    getLastExcDate()
    {
        return lastExcDate;
    }

    public void
    setLastExcDate(String _lastExcDate)
    {
        lastExcDate = _lastExcDate;
    }

    public String excEndDate;

    public String
    getExcEndDate()
    {
        return excEndDate;
    }

    public void
    setExcEndDate(String _excEndDate)
    {
        excEndDate = _excEndDate;
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

    public TransScheduleIce()
    {
        groupOrganId = "";
        groupOrganName = "";
        organId = "";
        organName = "";
        startDate = "";
        paramDate = "";
        runningStatus = "";
        runningStatusName = "";
        createDate = "";
        updateDate = "";
        excDate = "";
        lastExcDate = "";
        excEndDate = "";
        status = "";
    }

    public TransScheduleIce(String groupOrganId, String groupOrganName, String organId, String organName, String startDate, String paramDate, String runningStatus, String runningStatusName, String createDate, String updateDate, String excDate, String lastExcDate, String excEndDate, String status)
    {
        this.groupOrganId = groupOrganId;
        this.groupOrganName = groupOrganName;
        this.organId = organId;
        this.organName = organName;
        this.startDate = startDate;
        this.paramDate = paramDate;
        this.runningStatus = runningStatus;
        this.runningStatusName = runningStatusName;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.excDate = excDate;
        this.lastExcDate = lastExcDate;
        this.excEndDate = excEndDate;
        this.status = status;
    }

    public boolean
    equals(java.lang.Object rhs)
    {
        if(this == rhs)
        {
            return true;
        }
        TransScheduleIce _r = null;
        if(rhs instanceof TransScheduleIce)
        {
            _r = (TransScheduleIce)rhs;
        }

        if(_r != null)
        {
            if(groupOrganId != _r.groupOrganId)
            {
                if(groupOrganId == null || _r.groupOrganId == null || !groupOrganId.equals(_r.groupOrganId))
                {
                    return false;
                }
            }
            if(groupOrganName != _r.groupOrganName)
            {
                if(groupOrganName == null || _r.groupOrganName == null || !groupOrganName.equals(_r.groupOrganName))
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
            if(organName != _r.organName)
            {
                if(organName == null || _r.organName == null || !organName.equals(_r.organName))
                {
                    return false;
                }
            }
            if(startDate != _r.startDate)
            {
                if(startDate == null || _r.startDate == null || !startDate.equals(_r.startDate))
                {
                    return false;
                }
            }
            if(paramDate != _r.paramDate)
            {
                if(paramDate == null || _r.paramDate == null || !paramDate.equals(_r.paramDate))
                {
                    return false;
                }
            }
            if(runningStatus != _r.runningStatus)
            {
                if(runningStatus == null || _r.runningStatus == null || !runningStatus.equals(_r.runningStatus))
                {
                    return false;
                }
            }
            if(runningStatusName != _r.runningStatusName)
            {
                if(runningStatusName == null || _r.runningStatusName == null || !runningStatusName.equals(_r.runningStatusName))
                {
                    return false;
                }
            }
            if(createDate != _r.createDate)
            {
                if(createDate == null || _r.createDate == null || !createDate.equals(_r.createDate))
                {
                    return false;
                }
            }
            if(updateDate != _r.updateDate)
            {
                if(updateDate == null || _r.updateDate == null || !updateDate.equals(_r.updateDate))
                {
                    return false;
                }
            }
            if(excDate != _r.excDate)
            {
                if(excDate == null || _r.excDate == null || !excDate.equals(_r.excDate))
                {
                    return false;
                }
            }
            if(lastExcDate != _r.lastExcDate)
            {
                if(lastExcDate == null || _r.lastExcDate == null || !lastExcDate.equals(_r.lastExcDate))
                {
                    return false;
                }
            }
            if(excEndDate != _r.excEndDate)
            {
                if(excEndDate == null || _r.excEndDate == null || !excEndDate.equals(_r.excEndDate))
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

            return true;
        }

        return false;
    }

    public int
    hashCode()
    {
        int __h = 5381;
        __h = IceInternal.HashUtil.hashAdd(__h, "::segi::medicaltrans::common::report::transschedule::TransScheduleIce");
        __h = IceInternal.HashUtil.hashAdd(__h, groupOrganId);
        __h = IceInternal.HashUtil.hashAdd(__h, groupOrganName);
        __h = IceInternal.HashUtil.hashAdd(__h, organId);
        __h = IceInternal.HashUtil.hashAdd(__h, organName);
        __h = IceInternal.HashUtil.hashAdd(__h, startDate);
        __h = IceInternal.HashUtil.hashAdd(__h, paramDate);
        __h = IceInternal.HashUtil.hashAdd(__h, runningStatus);
        __h = IceInternal.HashUtil.hashAdd(__h, runningStatusName);
        __h = IceInternal.HashUtil.hashAdd(__h, createDate);
        __h = IceInternal.HashUtil.hashAdd(__h, updateDate);
        __h = IceInternal.HashUtil.hashAdd(__h, excDate);
        __h = IceInternal.HashUtil.hashAdd(__h, lastExcDate);
        __h = IceInternal.HashUtil.hashAdd(__h, excEndDate);
        __h = IceInternal.HashUtil.hashAdd(__h, status);
        return __h;
    }

    public TransScheduleIce
    clone()
    {
        TransScheduleIce c = null;
        try
        {
            c = (TransScheduleIce)super.clone();
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
        __os.writeString(groupOrganId);
        __os.writeString(groupOrganName);
        __os.writeString(organId);
        __os.writeString(organName);
        __os.writeString(startDate);
        __os.writeString(paramDate);
        __os.writeString(runningStatus);
        __os.writeString(runningStatusName);
        __os.writeString(createDate);
        __os.writeString(updateDate);
        __os.writeString(excDate);
        __os.writeString(lastExcDate);
        __os.writeString(excEndDate);
        __os.writeString(status);
    }

    public void
    __read(IceInternal.BasicStream __is)
    {
        groupOrganId = __is.readString();
        groupOrganName = __is.readString();
        organId = __is.readString();
        organName = __is.readString();
        startDate = __is.readString();
        paramDate = __is.readString();
        runningStatus = __is.readString();
        runningStatusName = __is.readString();
        createDate = __is.readString();
        updateDate = __is.readString();
        excDate = __is.readString();
        lastExcDate = __is.readString();
        excEndDate = __is.readString();
        status = __is.readString();
    }

    static public void
    __write(IceInternal.BasicStream __os, TransScheduleIce __v)
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

    static public TransScheduleIce
    __read(IceInternal.BasicStream __is, TransScheduleIce __v)
    {
        if(__v == null)
        {
             __v = new TransScheduleIce();
        }
        __v.__read(__is);
        return __v;
    }
    
    private static final TransScheduleIce __nullMarshalValue = new TransScheduleIce();

    public static final long serialVersionUID = -980730968L;
}
