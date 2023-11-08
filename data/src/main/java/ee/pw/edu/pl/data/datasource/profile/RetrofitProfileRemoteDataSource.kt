package ee.pw.edu.pl.data.datasource.profile

import ee.pw.edu.pl.data.core.remote.ApiService
import ee.pw.edu.pl.data.model.apiCallWithHeaders
import ee.pw.edu.pl.data.model.profile.SetProfileInfoRequest

class RetrofitProfileRemoteDataSource(
    private val api: ApiService,
) : ProfileRemoteDataSource {
    override suspend fun setInfo(request: SetProfileInfoRequest) = apiCallWithHeaders {
        api.setInfo(request)
    }
}
