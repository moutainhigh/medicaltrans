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
// Generated from file `mt_tasktype_common.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package segi.medicaltrans.common.transType;

public class TransTypeInfo implements java.lang.Cloneable, java.io.Serializable
{
    public String transTypeId;

    public String
    getTransTypeId()
    {
        return transTypeId;
    }

    public void
    setTransTypeId(String _transTypeId)
    {
        transTypeId = _transTypeId;
    }

    public String transTypeName;

    public String
    getTransTypeName()
    {
        return transTypeName;
    }

    public void
    setTransTypeName(String _transTypeName)
    {
        transTypeName = _transTypeName;
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

    public String warnMinite;

    public String
    getWarnMinite()
    {
        return warnMinite;
    }

    public void
    setWarnMinite(String _warnMinite)
    {
        warnMinite = _warnMinite;
    }

    public String standardMinite;

    public String
    getStandardMinite()
    {
        return standardMinite;
    }

    public void
    setStandardMinite(String _standardMinite)
    {
        standardMinite = _standardMinite;
    }

    public TransTypeInfo()
    {
        transTypeId = "";
        transTypeName = "";
        status = "";
        warnMinite = "";
        standardMinite = "";
    }

    public TransTypeInfo(String transTypeId, String transTypeName, String status, String warnMinite, String standardMinite)
    {
        this.transTypeId = transTypeId;
        this.transTypeName = transTypeName;
        this.status = status;
        this.warnMinite = warnMinite;
        this.standardMinite = standardMinite;
    }

    public boolean
    equals(java.lang.Object rhs)
    {
        if(this == rhs)
        {
            return true;
        }
        TransTypeInfo _r = null;
        if(rhs instanceof TransTypeInfo)
        {
            _r = (TransTypeInfo)rhs;
        }

        if(_r != null)
        {
            if(transTypeId != _r.transTypeId)
            {
                if(transTypeId == null || _r.transTypeId == null || !transTypeId.equals(_r.transTypeId))
                {
                    return false;
                }
            }
            if(transTypeName != _r.transTypeName)
            {
                if(transTypeName == null || _r.transTypeName == null || !transTypeName.equals(_r.transTypeName))
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
            if(warnMinite != _r.warnMinite)
            {
                if(warnMinite == null || _r.warnMinite == null || !warnMinite.equals(_r.warnMinite))
                {
                    return false;
                }
            }
            if(standardMinite != _r.standardMinite)
            {
                if(standardMinite == null || _r.standardMinite == null || !standardMinite.equals(_r.standardMinite))
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
        __h = IceInternal.HashUtil.hashAdd(__h, "::segi::medicaltrans::common::transType::TransTypeInfo");
        __h = IceInternal.HashUtil.hashAdd(__h, transTypeId);
        __h = IceInternal.HashUtil.hashAdd(__h, transTypeName);
        __h = IceInternal.HashUtil.hashAdd(__h, status);
        __h = IceInternal.HashUtil.hashAdd(__h, warnMinite);
        __h = IceInternal.HashUtil.hashAdd(__h, standardMinite);
        return __h;
    }

    public TransTypeInfo
    clone()
    {
        TransTypeInfo c = null;
        try
        {
            c = (TransTypeInfo)super.clone();
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
        __os.writeString(transTypeId);
        __os.writeString(transTypeName);
        __os.writeString(status);
        __os.writeString(warnMinite);
        __os.writeString(standardMinite);
    }

    public void
    __read(IceInternal.BasicStream __is)
    {
        transTypeId = __is.readString();
        transTypeName = __is.readString();
        status = __is.readString();
        warnMinite = __is.readString();
        standardMinite = __is.readString();
    }

    static public void
    __write(IceInternal.BasicStream __os, TransTypeInfo __v)
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

    static public TransTypeInfo
    __read(IceInternal.BasicStream __is, TransTypeInfo __v)
    {
        if(__v == null)
        {
             __v = new TransTypeInfo();
        }
        __v.__read(__is);
        return __v;
    }
    
    private static final TransTypeInfo __nullMarshalValue = new TransTypeInfo();

    public static final long serialVersionUID = 282275817L;
}