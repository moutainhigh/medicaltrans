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
// Generated from file `mt_personal_volume_month.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package segi.medicaltrans.report.pervolume.month;

public class CurUserRankIce implements java.lang.Cloneable, java.io.Serializable
{
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

    public CurUserRankIce()
    {
        userId = "";
        rank = "";
        transVolume = "";
    }

    public CurUserRankIce(String userId, String rank, String transVolume)
    {
        this.userId = userId;
        this.rank = rank;
        this.transVolume = transVolume;
    }

    public boolean
    equals(java.lang.Object rhs)
    {
        if(this == rhs)
        {
            return true;
        }
        CurUserRankIce _r = null;
        if(rhs instanceof CurUserRankIce)
        {
            _r = (CurUserRankIce)rhs;
        }

        if(_r != null)
        {
            if(userId != _r.userId)
            {
                if(userId == null || _r.userId == null || !userId.equals(_r.userId))
                {
                    return false;
                }
            }
            if(rank != _r.rank)
            {
                if(rank == null || _r.rank == null || !rank.equals(_r.rank))
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

            return true;
        }

        return false;
    }

    public int
    hashCode()
    {
        int __h = 5381;
        __h = IceInternal.HashUtil.hashAdd(__h, "::segi::medicaltrans::report::pervolume::month::CurUserRankIce");
        __h = IceInternal.HashUtil.hashAdd(__h, userId);
        __h = IceInternal.HashUtil.hashAdd(__h, rank);
        __h = IceInternal.HashUtil.hashAdd(__h, transVolume);
        return __h;
    }

    public CurUserRankIce
    clone()
    {
        CurUserRankIce c = null;
        try
        {
            c = (CurUserRankIce)super.clone();
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
        __os.writeString(userId);
        __os.writeString(rank);
        __os.writeString(transVolume);
    }

    public void
    __read(IceInternal.BasicStream __is)
    {
        userId = __is.readString();
        rank = __is.readString();
        transVolume = __is.readString();
    }

    static public void
    __write(IceInternal.BasicStream __os, CurUserRankIce __v)
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

    static public CurUserRankIce
    __read(IceInternal.BasicStream __is, CurUserRankIce __v)
    {
        if(__v == null)
        {
             __v = new CurUserRankIce();
        }
        __v.__read(__is);
        return __v;
    }
    
    private static final CurUserRankIce __nullMarshalValue = new CurUserRankIce();

    public static final long serialVersionUID = 1246213504L;
}
