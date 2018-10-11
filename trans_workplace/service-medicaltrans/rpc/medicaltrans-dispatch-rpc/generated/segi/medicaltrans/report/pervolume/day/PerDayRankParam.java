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
// Generated from file `mt_personal_volume_day.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package segi.medicaltrans.report.pervolume.day;

public class PerDayRankParam implements java.lang.Cloneable, java.io.Serializable
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

    public String beginTime;

    public String
    getBeginTime()
    {
        return beginTime;
    }

    public void
    setBeginTime(String _beginTime)
    {
        beginTime = _beginTime;
    }

    public String endTime;

    public String
    getEndTime()
    {
        return endTime;
    }

    public void
    setEndTime(String _endTime)
    {
        endTime = _endTime;
    }

    public String sergroupIds;

    public String
    getSergroupIds()
    {
        return sergroupIds;
    }

    public void
    setSergroupIds(String _sergroupIds)
    {
        sergroupIds = _sergroupIds;
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

    public PerDayRankParam()
    {
        organId = "";
        beginTime = "";
        endTime = "";
        sergroupIds = "";
        userId = "";
    }

    public PerDayRankParam(String organId, String beginTime, String endTime, String sergroupIds, String userId)
    {
        this.organId = organId;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.sergroupIds = sergroupIds;
        this.userId = userId;
    }

    public boolean
    equals(java.lang.Object rhs)
    {
        if(this == rhs)
        {
            return true;
        }
        PerDayRankParam _r = null;
        if(rhs instanceof PerDayRankParam)
        {
            _r = (PerDayRankParam)rhs;
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
            if(beginTime != _r.beginTime)
            {
                if(beginTime == null || _r.beginTime == null || !beginTime.equals(_r.beginTime))
                {
                    return false;
                }
            }
            if(endTime != _r.endTime)
            {
                if(endTime == null || _r.endTime == null || !endTime.equals(_r.endTime))
                {
                    return false;
                }
            }
            if(sergroupIds != _r.sergroupIds)
            {
                if(sergroupIds == null || _r.sergroupIds == null || !sergroupIds.equals(_r.sergroupIds))
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

            return true;
        }

        return false;
    }

    public int
    hashCode()
    {
        int __h = 5381;
        __h = IceInternal.HashUtil.hashAdd(__h, "::segi::medicaltrans::report::pervolume::day::PerDayRankParam");
        __h = IceInternal.HashUtil.hashAdd(__h, organId);
        __h = IceInternal.HashUtil.hashAdd(__h, beginTime);
        __h = IceInternal.HashUtil.hashAdd(__h, endTime);
        __h = IceInternal.HashUtil.hashAdd(__h, sergroupIds);
        __h = IceInternal.HashUtil.hashAdd(__h, userId);
        return __h;
    }

    public PerDayRankParam
    clone()
    {
        PerDayRankParam c = null;
        try
        {
            c = (PerDayRankParam)super.clone();
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
        __os.writeString(beginTime);
        __os.writeString(endTime);
        __os.writeString(sergroupIds);
        __os.writeString(userId);
    }

    public void
    __read(IceInternal.BasicStream __is)
    {
        organId = __is.readString();
        beginTime = __is.readString();
        endTime = __is.readString();
        sergroupIds = __is.readString();
        userId = __is.readString();
    }

    static public void
    __write(IceInternal.BasicStream __os, PerDayRankParam __v)
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

    static public PerDayRankParam
    __read(IceInternal.BasicStream __is, PerDayRankParam __v)
    {
        if(__v == null)
        {
             __v = new PerDayRankParam();
        }
        __v.__read(__is);
        return __v;
    }
    
    private static final PerDayRankParam __nullMarshalValue = new PerDayRankParam();

    public static final long serialVersionUID = -1796440653L;
}
