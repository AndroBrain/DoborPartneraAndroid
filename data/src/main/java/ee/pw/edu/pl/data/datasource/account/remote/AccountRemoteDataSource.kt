package ee.pw.edu.pl.data.datasource.account.remote

import ee.pw.edu.pl.data.model.ApiResponse
import ee.pw.edu.pl.data.model.account.remote.GetAccountInfoResponse
import ee.pw.edu.pl.data.model.account.remote.SetAccountInfoRequest

interface AccountRemoteDataSource {
    suspend fun setInfo(request: SetAccountInfoRequest): ApiResponse<Unit>
    suspend fun getInfo(): ApiResponse<GetAccountInfoResponse>
}
