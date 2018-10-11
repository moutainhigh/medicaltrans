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
// Generated from file `mt_task_track.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package segi.medicaltrans.mttask.track;

public class TrackDetailRspIce implements java.lang.Cloneable, java.io.Serializable
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

    public java.util.List<TrackDetailIce> resultList;

    public java.util.List<TrackDetailIce>
    getResultList()
    {
        return resultList;
    }

    public void
    setResultList(java.util.List<TrackDetailIce> _resultList)
    {
        resultList = _resultList;
    }

    public TrackDetailRspIce()
    {
        code = "";
        message = "";
    }

    public TrackDetailRspIce(String code, String message, java.util.List<TrackDetailIce> resultList)
    {
        this.code = code;
        this.message = message;
        this.resultList = resultList;
    }

    public boolean
    equals(java.lang.Object rhs)
    {
        if(this == rhs)
        {
            return true;
        }
        TrackDetailRspIce _r = null;
        if(rhs instanceof TrackDetailRspIce)
        {
            _r = (TrackDetailRspIce)rhs;
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
            if(resultList != _r.resultList)
            {
                if(resultList == null || _r.resultList == null || !resultList.equals(_r.resultList))
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
        __h = IceInternal.HashUtil.hashAdd(__h, "::segi::medicaltrans::mttask::track::TrackDetailRspIce");
        __h = IceInternal.HashUtil.hashAdd(__h, code);
        __h = IceInternal.HashUtil.hashAdd(__h, message);
        __h = IceInternal.HashUtil.hashAdd(__h, resultList);
        return __h;
    }

    public TrackDetailRspIce
    clone()
    {
        TrackDetailRspIce c = null;
        try
        {
            c = (TrackDetailRspIce)super.clone();
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
        ResultListHelper.write(__os, resultList);
    }

    public void
    __read(IceInternal.BasicStream __is)
    {
        code = __is.readString();
        message = __is.readString();
        resultList = ResultListHelper.read(__is);
    }

    static public void
    __write(IceInternal.BasicStream __os, TrackDetailRspIce __v)
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

    static public TrackDetailRspIce
    __read(IceInternal.BasicStream __is, TrackDetailRspIce __v)
    {
        if(__v == null)
        {
             __v = new TrackDetailRspIce();
        }
        __v.__read(__is);
        return __v;
    }
    
    private static final TrackDetailRspIce __nullMarshalValue = new TrackDetailRspIce();

    public static final long serialVersionUID = 319389203L;
}
