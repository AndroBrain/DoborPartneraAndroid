package ee.pw.edu.pl.data.datasource.profile.remote

import ee.pw.edu.pl.data.core.remote.ApiService
import ee.pw.edu.pl.data.model.ApiResponse
import ee.pw.edu.pl.data.model.apiCall
import ee.pw.edu.pl.data.model.profile.remote.GetProfileInfoResponse
import ee.pw.edu.pl.data.model.profile.remote.SetProfileInfoRequest

class RetrofitProfileRemoteDataSource(
    private val api: ApiService,
) : ProfileRemoteDataSource {
    override suspend fun setInfo(request: SetProfileInfoRequest) = apiCall {
        api.setInfo(request)
    }

    override suspend fun getInfo(): ApiResponse<GetProfileInfoResponse> =
        apiCall {
            api.getInfo()
        }
}
