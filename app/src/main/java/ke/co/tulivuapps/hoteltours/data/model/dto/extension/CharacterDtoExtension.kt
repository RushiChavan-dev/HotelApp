package ke.co.tulivuapps.hoteltours.data.model.dto.extension

import ke.co.tulivuapps.hoteltours.data.model.FavoriteEntity
import ke.co.tulivuapps.hoteltours.data.model.LocationEntity
import ke.co.tulivuapps.hoteltours.data.model.LocationResponse
import ke.co.tulivuapps.hoteltours.data.model.OriginResponse
import ke.co.tulivuapps.hoteltours.data.model.Result
import ke.co.tulivuapps.hoteltours.data.model.Status
import ke.co.tulivuapps.hoteltours.data.model.booking.ResultBooking
import ke.co.tulivuapps.hoteltours.data.model.character.ResultCharacter
import ke.co.tulivuapps.hoteltours.data.model.city.CityFavoriteEntity
import ke.co.tulivuapps.hoteltours.data.model.city.ResultCity
import ke.co.tulivuapps.hoteltours.data.model.destination.DestinationFavoriteEntity
import ke.co.tulivuapps.hoteltours.data.model.dto.BookingDto
import ke.co.tulivuapps.hoteltours.data.model.dto.CharacterDto
import ke.co.tulivuapps.hoteltours.data.model.dto.CityDto
import ke.co.tulivuapps.hoteltours.data.model.dto.DestinationDto
import ke.co.tulivuapps.hoteltours.data.model.dto.HotelDto
import ke.co.tulivuapps.hoteltours.data.model.dto.LocationDto
import ke.co.tulivuapps.hoteltours.data.model.dto.TravelStyleDto
import ke.co.tulivuapps.hoteltours.data.model.hotel.HotelFavoriteEntity
import ke.co.tulivuapps.hoteltours.data.model.hotel.ResultHotel
import ke.co.tulivuapps.hoteltours.data.model.img
import ke.co.tulivuapps.hoteltours.data.model.travelstyle.ResultTravelStyle
import ke.co.tulivuapps.hoteltours.data.model.travelstyle.TravelStyleFavoriteEntity

/**
 * Created by Rushi on 27.03.2023
 */

fun ResultBooking.toBookingDto() = BookingDto(
                                            id,
                                            createdAt,
                                            sessionId,
                                            hotellId,
                                            description ,
                                            startDate,
                                            endDate     ,
                                            img         ,
                                            lat         ,
                                            location    ,
                                            long        ,
                                            price       ,
                                            status      ,
                                            star        ,
                                            title       ,
                                            total       ,
                                            userEmail   ,
                                            cityId      ,
                                        )

fun ResultHotel.toHotelDto() = HotelDto(
    localId,
    id,
    title,
    description,
    star,
    lat,
    location,
    long,
    price,
    offer,
    offerPrice,
    userEmail,
    cityId,
    createdAt,
    travelStyleId,
    img
)

fun HotelFavoriteEntity.toHotelDto() = HotelDto(
    localId = localId ?: 0,
    id = id.orEmpty(),
    title = title.orEmpty(),
    description = description.orEmpty(),
    star = star.orEmpty(),
    lat = lat.orEmpty(),
    location = location.orEmpty(),
    lang = lang.orEmpty(),
    price = price.orEmpty(),
    offer = offer.orEmpty(),
    offerPrice = offerPrice.orEmpty(),
    userEmail = userEmail.orEmpty(),
    cityId = cityId.orEmpty(),
    createdAt = createdAt.orEmpty(),
    travelStyleId = travelStyleId.orEmpty(),
    img = null
)

fun CityFavoriteEntity.toCityDto() = CityDto(
    localId = localId ?: 0,
    id = id.orEmpty(),
    cityName = cityName,
    publicId = publicId,
    url = url,
    status = status
)


fun List<CityFavoriteEntity>.toCityFavoriteDtoList() = map { it.toCityDto() }

fun TravelStyleFavoriteEntity.toTravelStyleDto() = TravelStyleDto(
    localId = localId ?: 0,
    id = id.orEmpty(),
    styleName =  styleName,
    publicId =  publicId,
    url =  url,
    status =  status,
    img =  img,
    createdAt = createdAt.orEmpty()
)

fun List<TravelStyleFavoriteEntity>.toTravelStyleFavoriteDtoList() = map { it.toTravelStyleDto() }

fun List<ResultHotel>.toHotelDtoList() = map { it.toHotelDto() }

fun List<ResultBooking>.toBookingDtoList() = map { it.toBookingDto() }

fun List<HotelFavoriteEntity>.toHotelFavoriteDtoList() = map { it.toHotelDto() }

fun Result.toDestinationDto() = DestinationDto(
    localId,
    id,
    title,
    description,
    star,
    lat,
    location,
    long,
    price,
    offer,
    offerPrice,
    userEmail,
    cityId,
    createdAt,
    img
)

fun DestinationFavoriteEntity.toDestinationDto() = DestinationDto(
    localId = localId ?: 0,
    id = id,
    title = title.orEmpty(),
    description = description,
    star = star,
    lat = lat,
    location = location,
    lang = lang,
    price = price,
    offer = offer,
    offerPrice = offerPrice,
    userEmail = userEmail,
    cityId = cityId,
    createdAt = createdAt,
    img = null
)


fun List<Result>.toDestinationDtoList() = map { it.toDestinationDto() }

fun List<DestinationFavoriteEntity>.toDestinationFavoriteDtoList() = map { it.toDestinationDto() }

fun ResultCharacter.toCharacterDto() = CharacterDto(
    created,
    episode,
    gender,
    id,
    image,
    location.toLocationDto(),
    name,
    origin.toLocationDto(),
    species,
    status,
    type,
    url
)
fun ResultCity.toCityDto() = CityDto(
    localId,
    id,
    cityName,
    publicId,
    url,
    status
)
fun ResultTravelStyle.toTravelStyleDto() = TravelStyleDto(
    localId,
    id,
    styleName,
    publicId,
    url,
    status,
    createdAt,
    img
)

fun List<ResultCharacter>.toCharacterDtoList() = map { it.toCharacterDto() }
fun List<ResultCity>.toCityDtoList() = map { it.toCityDto() }
fun List<ResultTravelStyle>.toTravelStyleDtoList() = map { it.toTravelStyleDto() }

fun FavoriteEntity.toCharacterDto() = CharacterDto(
    created,
    episode,
    gender,
    id,
    image,
    location?.toLocationDto(),
    name,
    origin?.toLocationDto(),
    species,
    status,
    type,
    url
)

fun LocationEntity.toLocationDto() = LocationDto(
    locationId = url.getIdFromUrl(),
    name = name,
    url = url
)

fun LocationResponse.toLocationDto() = LocationDto(
    locationId = url?.getIdFromUrl() ?: 0,
    name = name.toString(),
    url = url.toString()
)

fun OriginResponse.toLocationDto() = LocationDto(
    locationId = url?.getIdFromUrl() ?: 0,
    name = name.toString(),
    url = url.toString()
)

fun String.getIdFromUrl(): Int = substring(lastIndexOf("/") + 1).toIntOrNull() ?: 0

fun List<FavoriteEntity>.toFavoriteDtoList() = map { it.toCharacterDto() }

fun LocationDto.toLocationDto() = LocationEntity(
    locationId = url.getIdFromUrl() ?: 0,
    name = name,
    url = url
)

fun CharacterDto.toFavoriteEntity() = FavoriteEntity(
    id = id ?: 0,
    name = name.orEmpty(),
    image = image.orEmpty(),
    created = created.orEmpty(),
    origin = origin?.toLocationDto(),
    location = location?.toLocationDto(),
    status = status ?: Status.Unknown,
    species = species.orEmpty(),
    gender = gender.orEmpty(),
    type = type.orEmpty(),
    url = url.orEmpty(),
    episode = episode.orEmpty()
)

fun DestinationDto.toDestinationFavoriteEntity() = DestinationFavoriteEntity(
    localId = localId ?: 0,
    id = id.orEmpty(),
    title = title.orEmpty(),
    description = description.orEmpty(),
    star = star.orEmpty(),
    lat = lat.orEmpty(),
    location = location.orEmpty(),
    lang = lang.orEmpty(),
    price = price.orEmpty(),
    offer = offer.orEmpty(),
    offerPrice = offerPrice.orEmpty(),
    userEmail = userEmail.orEmpty(),
    cityId = cityId.orEmpty(),
    createdAt = createdAt.orEmpty(),
    img = null
)

fun HotelDto.toHotelFavoriteEntity() = HotelFavoriteEntity(
    localId = localId ?: 0,
    id = id.orEmpty(),
    title = title,
    description = description,
    star = star,
    lat = lat,
    location = location,
    lang = lang,
    price = price,
    offer = offer,
    offerPrice = offerPrice,
    userEmail = userEmail,
    cityId = cityId,
    createdAt = createdAt,
    travelStyleId = travelStyleId,
    img = null
)

fun CityDto.toCityFavoriteEntity() = CityFavoriteEntity(
    localId = localId ?: 0,
    id = id.orEmpty(),
    cityName = cityName,
    publicId =  publicId,
    url =  url,
    status =  status,
)
fun TravelStyleDto.toTravelStyleFavoriteEntity() = TravelStyleFavoriteEntity(
    localId = localId ?: 0,
    id = id.orEmpty(),
    styleName = styleName.orEmpty(),
    publicId = publicId.orEmpty(),
    url = url.orEmpty(),
    status = status.orEmpty(),
    img = img.orEmpty(),
    createdAt = createdAt.orEmpty()
)

fun img.toImgDto() = img(
    id = id ?: "0",
    publicId = publicId,
    url = url
)

fun List<img>.toImgDtoList() = map { it.toImgDto() }


//@ExperimentalFoundationApi
//public fun <T: Any> VerticalGrid.items(
//    lazyPagingItems: LazyPagingItems<T>,
//    itemContent: @Composable LazyItemScope.(value: T?) -> Unit
//) {
//    items(lazyPagingItems.itemCount) { index ->
//        itemContent(lazyPagingItems[index])
//    }
//}