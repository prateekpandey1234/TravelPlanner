package com.example.travelplanner.repository

import com.example.travelplanner.data.local.TravelItem
import com.example.travelplanner.data.remote.ImageResponse
//fake repo for testing
/** * DAO is an abstraction of data persistence. However, a repository is an abstraction of a collection of objects
 * * DAO is a lower-level concept, closer to the storage systems. However, Repository is a higher-level concept, closer to the Domain objects
 *  * DAO works as a data mapping/access layer, hiding ugly queries. However, a repository is a layer between domains and data access layers, hiding the complexity of collating data and preparing a domain object
 *  * DAO can't be implemented using a repository. However, a repository can use a DAO for accessing underlying storage*/
interface DefaultRepository {
    suspend fun getImageList(Imagequery:String):com.example.travelplanner.other.Resource<ImageResponse>

    suspend fun insertTravelItem(travelItem: TravelItem)

    suspend fun deletTravelItem(travelItem: TravelItem)

}