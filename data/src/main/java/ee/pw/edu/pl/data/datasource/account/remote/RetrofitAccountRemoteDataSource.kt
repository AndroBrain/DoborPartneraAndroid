package ee.pw.edu.pl.data.datasource.account.remote

import ee.pw.edu.pl.data.core.remote.ApiService
import ee.pw.edu.pl.data.model.ApiResponse
import ee.pw.edu.pl.data.model.account.remote.GetAccountInfoResponse
import ee.pw.edu.pl.data.model.account.remote.SetAccountInfoRequest
import ee.pw.edu.pl.data.model.account.remote.SetTestRequest
import ee.pw.edu.pl.data.model.apiCall

class RetrofitAccountRemoteDataSource(
    private val api: ApiService,
) : AccountRemoteDataSource {
    override suspend fun setInfo(request: SetAccountInfoRequest) = apiCall {
        api.setInfo(request)
    }

    override suspend fun getInfo(): ApiResponse<GetAccountInfoResponse> =
        apiCall {
            api.getInfo()
        }

    override suspend fun setTest(request: SetTestRequest): ApiResponse<Unit> =
        apiCall {
            api.setTest(request)
        }
}
