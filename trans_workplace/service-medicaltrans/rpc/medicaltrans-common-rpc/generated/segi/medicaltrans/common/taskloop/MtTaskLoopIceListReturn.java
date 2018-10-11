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
// Generated from file `mt_task_loop_common.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package segi.medicaltrans.common.taskloop;

public class MtTaskLoopIceListReturn implements java.lang.Cloneable, java.io.Serializable
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

    public String msg;

    public String
    getMsg()
    {
        return msg;
    }

    public void
    setMsg(String _msg)
    {
        msg = _msg;
    }

    public java.util.List<MtTaskLoopIce> resultList;

    public java.util.List<MtTaskLoopIce>
    getResultList()
    {
        return resultList;
    }

    public void
    setResultList(java.util.List<MtTaskLoopIce> _resultList)
    {
        resultList = _resultList;
    }

    public MtTaskLoopIceListReturn()
    {
        code = "";
        msg = "";
    }

    public MtTaskLoopIceListReturn(String code, String msg, java.util.List<MtTaskLoopIce> resultList)
    {
        this.code = code;
        this.msg = msg;
        this.resultList = resultList;
    }

    public boolean
    equals(java.lang.Object rhs)
    {
        if(this == rhs)
        {
            return true;
        }
        MtTaskLoopIceListReturn _r = null;
        if(rhs instanceof MtTaskLoopIceListReturn)
        {
            _r = (MtTaskLoopIceListReturn)rhs;
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
            if(msg != _r.msg)
            {
                if(msg == null || _r.msg == null || !msg.equals(_r.msg))
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
        __h = IceInternal.HashUtil.hashAdd(__h, "::segi::medicaltrans::common::taskloop::MtTaskLoopIceListReturn");
        __h = IceInternal.HashUtil.hashAdd(__h, code);
        __h = IceInternal.HashUtil.hashAdd(__h, msg);
        __h = IceInternal.HashUtil.hashAdd(__h, resultList);
        return __h;
    }

    public MtTaskLoopIceListReturn
    clone()
    {
        MtTaskLoopIceListReturn c = null;
        try
        {
            c = (MtTaskLoopIceListReturn)super.clone();
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
        __os.writeString(msg);
        MtTaskLoopIceListHelper.write(__os, resultList);
    }

    public void
    __read(IceInternal.BasicStream __is)
    {
        code = __is.readString();
        msg = __is.readString();
        resultList = MtTaskLoopIceListHelper.read(__is);
    }

    static public void
    __write(IceInternal.BasicStream __os, MtTaskLoopIceListReturn __v)
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

    static public MtTaskLoopIceListReturn
    __read(IceInternal.BasicStream __is, MtTaskLoopIceListReturn __v)
    {
        if(__v == null)
        {
             __v = new MtTaskLoopIceListReturn();
        }
        __v.__read(__is);
        return __v;
    }
    
    private static final MtTaskLoopIceListReturn __nullMarshalValue = new MtTaskLoopIceListReturn();

    public static final long serialVersionUID = 1897434921L;
}