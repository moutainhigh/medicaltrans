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

public class MtTaskDetailRetIceRsp implements java.lang.Cloneable, java.io.Serializable
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

    public MtTaskDetailRetIce mtTaskDetailRetIce;

    public MtTaskDetailRetIce
    getMtTaskDetailRetIce()
    {
        return mtTaskDetailRetIce;
    }

    public void
    setMtTaskDetailRetIce(MtTaskDetailRetIce _mtTaskDetailRetIce)
    {
        mtTaskDetailRetIce = _mtTaskDetailRetIce;
    }

    public MtTaskDetailRetIceRsp()
    {
        code = "";
        message = "";
    }

    public MtTaskDetailRetIceRsp(String code, String message, MtTaskDetailRetIce mtTaskDetailRetIce)
    {
        this.code = code;
        this.message = message;
        this.mtTaskDetailRetIce = mtTaskDetailRetIce;
    }

    public boolean
    equals(java.lang.Object rhs)
    {
        if(this == rhs)
        {
            return true;
        }
        MtTaskDetailRetIceRsp _r = null;
        if(rhs instanceof MtTaskDetailRetIceRsp)
        {
            _r = (MtTaskDetailRetIceRsp)rhs;
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
            if(mtTaskDetailRetIce != _r.mtTaskDetailRetIce)
            {
                if(mtTaskDetailRetIce == null || _r.mtTaskDetailRetIce == null || !mtTaskDetailRetIce.equals(_r.mtTaskDetailRetIce))
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
        __h = IceInternal.HashUtil.hashAdd(__h, "::segi::medicaltrans::mttask::query::MtTaskDetailRetIceRsp");
        __h = IceInternal.HashUtil.hashAdd(__h, code);
        __h = IceInternal.HashUtil.hashAdd(__h, message);
        __h = IceInternal.HashUtil.hashAdd(__h, mtTaskDetailRetIce);
        return __h;
    }

    public MtTaskDetailRetIceRsp
    clone()
    {
        MtTaskDetailRetIceRsp c = null;
        try
        {
            c = (MtTaskDetailRetIceRsp)super.clone();
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
        __os.writeObject(mtTaskDetailRetIce);
    }

    private class Patcher implements IceInternal.Patcher
    {
        public void
        patch(Ice.Object v)
        {
            if(v == null || v instanceof MtTaskDetailRetIce)
            {
                mtTaskDetailRetIce = (MtTaskDetailRetIce)v;
            }
            else
            {
                IceInternal.Ex.throwUOE(type(), v);
            }
        }

        public String
        type()
        {
            return "::segi::medicaltrans::mttask::query::MtTaskDetailRetIce";
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
    __write(IceInternal.BasicStream __os, MtTaskDetailRetIceRsp __v)
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

    static public MtTaskDetailRetIceRsp
    __read(IceInternal.BasicStream __is, MtTaskDetailRetIceRsp __v)
    {
        if(__v == null)
        {
             __v = new MtTaskDetailRetIceRsp();
        }
        __v.__read(__is);
        return __v;
    }
    
    private static final MtTaskDetailRetIceRsp __nullMarshalValue = new MtTaskDetailRetIceRsp();

    public static final long serialVersionUID = 929248652L;
}
