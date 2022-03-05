package com.example.travelplanner.di


import android.content.Context
import androidx.room.Room
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.travelplanner.R
import com.example.travelplanner.data.local.TravelDB
import com.example.travelplanner.data.remote.PixabayAPI
import com.example.travelplanner.other.Constant.BASE_URL
import com.example.travelplanner.other.Constant.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object TravelAppModule {
   @Singleton
   @Provides
   fun provideTraveldatabase(
       @ApplicationContext context: Context
   ) = Room.databaseBuilder(context,TravelDB::class.java,DATABASE_NAME).build()

   @Singleton
   @Provides
   fun provideTravelDAO(
       database:TravelDB
   )=database.traveldao()

   @Singleton
   @Provides
   fun providePixabayclient():PixabayAPI{
       return Retrofit.Builder()
           .addConverterFactory(GsonConverterFactory.create())
           .baseUrl(BASE_URL)
           .build()
           .create(PixabayAPI::class.java)
   }

   @Singleton
   @Provides
   fun provideGlideImage(
       @ApplicationContext context:Context
   )=Glide.with(context).setDefaultRequestOptions(
       RequestOptions()
           .error(R.drawable.error_image)
   )


}