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

public class MtAutonomousTaskDetailIceRsp implements java.lang.Cloneable, java.io.Serializable
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

    public MtAutonomousTaskDetailIce mtAutonomousTaskDetailIce;

    public MtAutonomousTaskDetailIce
    getMtAutonomousTaskDetailIce()
    {
        return mtAutonomousTaskDetailIce;
    }

    public void
    setMtAutonomousTaskDetailIce(MtAutonomousTaskDetailIce _mtAutonomousTaskDetailIce)
    {
        mtAutonomousTaskDetailIce = _mtAutonomousTaskDetailIce;
    }

    public MtAutonomousTaskDetailIceRsp()
    {
        code = "";
        message = "";
    }

    public MtAutonomousTaskDetailIceRsp(String code, String message, MtAutonomousTaskDetailIce mtAutonomousTaskDetailIce)
    {
        this.code = code;
        this.message = message;
        this.mtAutonomousTaskDetailIce = mtAutonomousTaskDetailIce;
    }

    public boolean
    equals(java.lang.Object rhs)
    {
        if(this == rhs)
        {
            return true;
        }
        MtAutonomousTaskDetailIceRsp _r = null;
        if(rhs instanceof MtAutonomousTaskDetailIceRsp)
        {
            _r = (MtAutonomousTaskDetailIceRsp)rhs;
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
            if(mtAutonomousTaskDetailIce != _r.mtAutonomousTaskDetailIce)
            {
                if(mtAutonomousTaskDetailIce == null || _r.mtAutonomousTaskDetailIce == null || !mtAutonomousTaskDetailIce.equals(_r.mtAutonomousTaskDetailIce))
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
        __h = IceInternal.HashUtil.hashAdd(__h, "::segi::medicaltrans::mttask::query::MtAutonomousTaskDetailIceRsp");
        __h = IceInternal.HashUtil.hashAdd(__h, code);
        __h = IceInternal.HashUtil.hashAdd(__h, message);
        __h = IceInternal.HashUtil.hashAdd(__h, mtAutonomousTaskDetailIce);
        return __h;
    }

    public MtAutonomousTaskDetailIceRsp
    clone()
    {
        MtAutonomousTaskDetailIceRsp c = null;
        try
        {
            c = (MtAutonomousTaskDetailIceRsp)super.clone();
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
        __os.writeObject(mtAutonomousTaskDetailIce);
    }

    private class Patcher implements IceInternal.Patcher
    {
        public void
        patch(Ice.Object v)
        {
            if(v == null || v instanceof MtAutonomousTaskDetailIce)
            {
                mtAutonomousTaskDetailIce = (MtAutonomousTaskDetailIce)v;
            }
            else
            {
                IceInternal.Ex.throwUOE(type(), v);
            }
        }

        public String
        type()
        {
            return "::segi::medicaltrans::mttask::query::MtAutonomousTaskDetailIce";
        }
    }

    public void
    __read(IceInternal.BasicStream __is)
    {
        code = __is.readString();
        message = __is.readString();
        __is.readObject(new Patcher());
    }

    static public void
    __write(IceInternal.BasicStream __os, MtAutonomousTaskDetailIceRsp __v)
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

    static public MtAutonomousTaskDetailIceRsp
    __read(IceInternal.BasicStream __is, MtAutonomousTaskDetailIceRsp __v)
    {
        if(__v == null)
        {
             __v = new MtAutonomousTaskDetailIceRsp();
        }
        __v.__read(__is);
        return __v;
    }
    
    private static final MtAutonomousTaskDetailIceRsp __nullMarshalValue = new MtAutonomousTaskDetailIceRsp();

    public static final long serialVersionUID = 1184987084L;
}