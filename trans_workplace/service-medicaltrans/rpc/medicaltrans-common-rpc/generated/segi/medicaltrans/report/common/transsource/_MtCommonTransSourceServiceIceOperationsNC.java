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
// Generated from file `mt_transsource_common.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package segi.medicaltrans.report.common.transsource;

public interface _MtCommonTransSourceServiceIceOperationsNC
{
    resp.RpcRespIce saveTransSource(TransSourceInfo transSourceInfo);

    resp.RpcRespIce updateTransSourceById(int id, TransSourceInfo transSourceInfo);

    resp.RpcRespIce deleteTransSourceById(int id);

    TransSourceInfoReturnIce getTransSourceById(int id);
}