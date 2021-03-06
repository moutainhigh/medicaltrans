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

public class PerVolDayIce implements java.lang.Cloneable, java.io.Serializable
{
    public String rank;

    public String
    getRank()
    {
        return rank;
    }

    public void
    setRank(String _rank)
    {
        rank = _rank;
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

    public String userName;

    public String
    getUserName()
    {
        return userName;
    }

    public void
    setUserName(String _userName)
    {
        userName = _userName;
    }

    public String userNo;

    public String
    getUserNo()
    {
        return userNo;
    }

    public void
    setUserNo(String _userNo)
    {
        userNo = _userNo;
    }

    public String sergroupId;

    public String
    getSergroupId()
    {
        return sergroupId;
    }

    public void
    setSergroupId(String _sergroupId)
    {
        sergroupId = _sergroupId;
    }

    public String sergroupName;

    public String
    getSergroupName()
    {
        return sergroupName;
    }

    public void
    setSergroupName(String _sergroupName)
    {
        sergroupName = _sergroupName;
    }

    public String transVolume;

    public String
    getTransVolume()
    {
        return transVolume;
    }

    public void
    setTransVolume(String _transVolume)
    {
        transVolume = _transVolume;
    }

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

    public PerVolDayIce()
    {
        rank = "";
        userId = "";
        userName = "";
        userNo = "";
        sergroupId = "";
        sergroupName = "";
        transVolume = "";
        groupOrganId = "";
        organId = "";
        organName = "";
    }

    public PerVolDayIce(String rank, String userId, String userName, String userNo, String sergroupId, String sergroupName, String transVolume, String groupOrganId, String organId, String organName)
    {
        this.rank = rank;
        this.userId = userId;
        this.userName = userName;
        this.userNo = userNo;
        this.sergroupId = sergroupId;
        this.sergroupName = sergroupName;
        this.transVolume = transVolume;
        this.groupOrganId = groupOrganId;
        this.organId = organId;
        this.organName = organName;
    }

    public boolean
    equals(java.lang.Object rhs)
    {
        if(this == rhs)
        {
            return true;
        }
        PerVolDayIce _r = null;
        if(rhs instanceof PerVolDayIce)
        {
            _r = (PerVolDayIce)rhs;
        }

        if(_r != null)
        {
            if(rank != _r.rank)
            {
                if(rank == null || _r.rank == null || !rank.equals(_r.rank))
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
            if(userName != _r.userName)
            {
                if(userName == null || _r.userName == null || !userName.equals(_r.userName))
                {
                    return false;
                }
            }
            if(userNo != _r.userNo)
            {
                if(userNo == null || _r.userNo == null || !userNo.equals(_r.userNo))
                {
                    return false;
                }
            }
            if(sergroupId != _r.sergroupId)
            {
                if(sergroupId == null || _r.sergroupId == null || !sergroupId.equals(_r.sergroupId))
                {
                    return false;
                }
            }
            if(sergroupName != _r.sergroupName)
            {
                if(sergroupName == null || _r.sergroupName == null || !sergroupName.equals(_r.sergroupName))
                {
                    return false;
                }
            }
            if(transVolume != _r.transVolume)
            {
                if(transVolume == null || _r.transVolume == null || !transVolume.equals(_r.transVolume))
                {
                    return false;
                }
            }
            if(groupOrganId != _r.groupOrganId)
            {
                if(groupOrganId == null || _r.groupOrganId == null || !groupOrganId.equals(_r.groupOrganId))
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

            return true;
        }

        return false;
    }

    public int
    hashCode()
    {
        int __h = 5381;
        __h = IceInternal.HashUtil.hashAdd(__h, "::segi::medicaltrans::report::pervolume::day::PerVolDayIce");
        __h = IceInternal.HashUtil.hashAdd(__h, rank);
        __h = IceInternal.HashUtil.hashAdd(__h, userId);
        __h = IceInternal.HashUtil.hashAdd(__h, userName);
        __h = IceInternal.HashUtil.hashAdd(__h, userNo);
        __h = IceInternal.HashUtil.hashAdd(__h, sergroupId);
        __h = IceInternal.HashUtil.hashAdd(__h, sergroupName);
        __h = IceInternal.HashUtil.hashAdd(__h, transVolume);
        __h = IceInternal.HashUtil.hashAdd(__h, groupOrganId);
        __h = IceInternal.HashUtil.hashAdd(__h, organId);
        __h = IceInternal.HashUtil.hashAdd(__h, organName);
        return __h;
    }

    public PerVolDayIce
    clone()
    {
        PerVolDayIce c = null;
        try
        {
            c = (PerVolDayIce)super.clone();
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
        __os.writeString(rank);
        __os.writeString(userId);
        __os.writeString(userName);
        __os.writeString(userNo);
        __os.writeString(sergroupId);
        __os.writeString(sergroupName);
        __os.writeString(transVolume);
        __os.writeString(groupOrganId);
        __os.writeString(organId);
        __os.writeString(organName);
    }

    public void
    __read(IceInternal.BasicStream __is)
    {
        rank = __is.readString();
        userId = __is.readString();
        userName = __is.readString();
        userNo = __is.readString();
        sergroupId = __is.readString();
        sergroupName = __is.readString();
        transVolume = __is.readString();
        groupOrganId = __is.readString();
        organId = __is.readString();
        organName = __is.readString();
    }

    static public void
    __write(IceInternal.BasicStream __os, PerVolDayIce __v)
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

    static public PerVolDayIce
    __read(IceInternal.BasicStream __is, PerVolDayIce __v)
    {
        if(__v == null)
        {
             __v = new PerVolDayIce();
        }
        __v.__read(__is);
        return __v;
    }
    
    private static final PerVolDayIce __nullMarshalValue = new PerVolDayIce();

    public static final long serialVersionUID = 1200550887L;
}
