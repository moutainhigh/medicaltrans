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
// Generated from file `mt_trans_statistics.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package segi.medicaltrans.report.trans;

public class TransRatioReturnIce implements java.lang.Cloneable, java.io.Serializable
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

    public java.util.List<TransRatioIce> resultList;

    public java.util.List<TransRatioIce>
    getResultList()
    {
        return resultList;
    }

    public void
    setResultList(java.util.List<TransRatioIce> _resultList)
    {
        resultList = _resultList;
    }

    public TransRatioReturnIce()
    {
        code = "";
        msg = "";
    }

    public TransRatioReturnIce(String code, String msg, java.util.List<TransRatioIce> resultList)
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
        TransRatioReturnIce _r = null;
        if(rhs instanceof TransRatioReturnIce)
        {
            _r = (TransRatioReturnIce)rhs;
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
        __h = IceInternal.HashUtil.hashAdd(__h, "::segi::medicaltrans::report::trans::TransRatioReturnIce");
        __h = IceInternal.HashUtil.hashAdd(__h, code);
        __h = IceInternal.HashUtil.hashAdd(__h, msg);
        __h = IceInternal.HashUtil.hashAdd(__h, resultList);
        return __h;
    }

    public TransRatioReturnIce
    clone()
    {
        TransRatioReturnIce c = null;
        try
        {
            c = (TransRatioReturnIce)super.clone();
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
        TransRatioIceListHelper.write(__os, resultList);
    }

    public void
    __read(IceInternal.BasicStream __is)
    {
        code = __is.readString();
        msg = __is.readString();
        resultList = TransRatioIceListHelper.read(__is);
    }

    static public void
    __write(IceInternal.BasicStream __os, TransRatioReturnIce __v)
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

    static public TransRatioReturnIce
    __read(IceInternal.BasicStream __is, TransRatioReturnIce __v)
    {
        if(__v == null)
        {
             __v = new TransRatioReturnIce();
        }
        __v.__read(__is);
        return __v;
    }
    
    private static final TransRatioReturnIce __nullMarshalValue = new TransRatioReturnIce();

    public static final long serialVersionUID = -637227568L;
}