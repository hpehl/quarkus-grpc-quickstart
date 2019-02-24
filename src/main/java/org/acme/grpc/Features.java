package org.acme.grpc;

import java.util.Iterator;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;

import static java.util.Collections.emptyList;

@ApplicationScoped
public class Features implements Iterable<Feature> {

    private static final String JSON = "{\n" +
            "  \"feature\": [{\n" +
            "    \"location\": {\n" +
            "      \"latitude\": 407838351,\n" +
            "      \"longitude\": -746143763\n" +
            "    },\n" +
            "    \"name\": \"Patriots Path, Mendham, NJ 07945, USA\"\n" +
            "  }, {\n" +
            "    \"location\": {\n" +
            "      \"latitude\": 408122808,\n" +
            "      \"longitude\": -743999179\n" +
            "    },\n" +
            "    \"name\": \"101 New Jersey 10, Whippany, NJ 07981, USA\"\n" +
            "  }, {\n" +
            "    \"location\": {\n" +
            "      \"latitude\": 413628156,\n" +
            "      \"longitude\": -749015468\n" +
            "    },\n" +
            "    \"name\": \"U.S. 6, Shohola, PA 18458, USA\"\n" +
            "  }, {\n" +
            "    \"location\": {\n" +
            "      \"latitude\": 419999544,\n" +
            "      \"longitude\": -740371136\n" +
            "    },\n" +
            "    \"name\": \"5 Conners Road, Kingston, NY 12401, USA\"\n" +
            "  }, {\n" +
            "    \"location\": {\n" +
            "      \"latitude\": 414008389,\n" +
            "      \"longitude\": -743951297\n" +
            "    },\n" +
            "    \"name\": \"Mid Hudson Psychiatric Center, New Hampton, NY 10958, USA\"\n" +
            "  }, {\n" +
            "    \"location\": {\n" +
            "      \"latitude\": 419611318,\n" +
            "      \"longitude\": -746524769\n" +
            "    },\n" +
            "    \"name\": \"287 Flugertown Road, Livingston Manor, NY 12758, USA\"\n" +
            "  }, {\n" +
            "    \"location\": {\n" +
            "      \"latitude\": 406109563,\n" +
            "      \"longitude\": -742186778\n" +
            "    },\n" +
            "    \"name\": \"4001 Tremley Point Road, Linden, NJ 07036, USA\"\n" +
            "  }, {\n" +
            "    \"location\": {\n" +
            "      \"latitude\": 416802456,\n" +
            "      \"longitude\": -742370183\n" +
            "    },\n" +
            "    \"name\": \"352 South Mountain Road, Wallkill, NY 12589, USA\"\n" +
            "  }, {\n" +
            "    \"location\": {\n" +
            "      \"latitude\": 412950425,\n" +
            "      \"longitude\": -741077389\n" +
            "    },\n" +
            "    \"name\": \"Bailey Turn Road, Harriman, NY 10926, USA\"\n" +
            "  }, {\n" +
            "    \"location\": {\n" +
            "      \"latitude\": 412144655,\n" +
            "      \"longitude\": -743949739\n" +
            "    },\n" +
            "    \"name\": \"193-199 Wawayanda Road, Hewitt, NJ 07421, USA\"\n" +
            "  }, {\n" +
            "    \"location\": {\n" +
            "      \"latitude\": 415736605,\n" +
            "      \"longitude\": -742847522\n" +
            "    },\n" +
            "    \"name\": \"406-496 Ward Avenue, Pine Bush, NY 12566, USA\"\n" +
            "  }, {\n" +
            "    \"location\": {\n" +
            "      \"latitude\": 413843930,\n" +
            "      \"longitude\": -740501726\n" +
            "    },\n" +
            "    \"name\": \"162 Merrill Road, Highland Mills, NY 10930, USA\"\n" +
            "  }, {\n" +
            "    \"location\": {\n" +
            "      \"latitude\": 410873075,\n" +
            "      \"longitude\": -744459023\n" +
            "    },\n" +
            "    \"name\": \"Clinton Road, West Milford, NJ 07480, USA\"\n" +
            "  }, {\n" +
            "    \"location\": {\n" +
            "      \"latitude\": 412346009,\n" +
            "      \"longitude\": -744026814\n" +
            "    },\n" +
            "    \"name\": \"16 Old Brook Lane, Warwick, NY 10990, USA\"\n" +
            "  }, {\n" +
            "    \"location\": {\n" +
            "      \"latitude\": 402948455,\n" +
            "      \"longitude\": -747903913\n" +
            "    },\n" +
            "    \"name\": \"3 Drake Lane, Pennington, NJ 08534, USA\"\n" +
            "  }, {\n" +
            "    \"location\": {\n" +
            "      \"latitude\": 406337092,\n" +
            "      \"longitude\": -740122226\n" +
            "    },\n" +
            "    \"name\": \"6324 8th Avenue, Brooklyn, NY 11220, USA\"\n" +
            "  }, {\n" +
            "    \"location\": {\n" +
            "      \"latitude\": 406421967,\n" +
            "      \"longitude\": -747727624\n" +
            "    },\n" +
            "    \"name\": \"1 Merck Access Road, Whitehouse Station, NJ 08889, USA\"\n" +
            "  }, {\n" +
            "    \"location\": {\n" +
            "      \"latitude\": 416318082,\n" +
            "      \"longitude\": -749677716\n" +
            "    },\n" +
            "    \"name\": \"78-98 Schalck Road, Narrowsburg, NY 12764, USA\"\n" +
            "  }, {\n" +
            "    \"location\": {\n" +
            "      \"latitude\": 415301720,\n" +
            "      \"longitude\": -748416257\n" +
            "    },\n" +
            "    \"name\": \"282 Lakeview Drive Road, Highland Lake, NY 12743, USA\"\n" +
            "  }, {\n" +
            "    \"location\": {\n" +
            "      \"latitude\": 402647019,\n" +
            "      \"longitude\": -747071791\n" +
            "    },\n" +
            "    \"name\": \"330 Evelyn Avenue, Hamilton Township, NJ 08619, USA\"\n" +
            "  }, {\n" +
            "    \"location\": {\n" +
            "      \"latitude\": 412567807,\n" +
            "      \"longitude\": -741058078\n" +
            "    },\n" +
            "    \"name\": \"New York State Reference Route 987E, Southfields, NY 10975, USA\"\n" +
            "  }, {\n" +
            "    \"location\": {\n" +
            "      \"latitude\": 416855156,\n" +
            "      \"longitude\": -744420597\n" +
            "    },\n" +
            "    \"name\": \"103-271 Tempaloni Road, Ellenville, NY 12428, USA\"\n" +
            "  }, {\n" +
            "    \"location\": {\n" +
            "      \"latitude\": 404663628,\n" +
            "      \"longitude\": -744820157\n" +
            "    },\n" +
            "    \"name\": \"1300 Airport Road, North Brunswick Township, NJ 08902, USA\"\n" +
            "  }, {\n" +
            "    \"location\": {\n" +
            "      \"latitude\": 407113723,\n" +
            "      \"longitude\": -749746483\n" +
            "    },\n" +
            "    \"name\": \"\"\n" +
            "  }, {\n" +
            "    \"location\": {\n" +
            "      \"latitude\": 402133926,\n" +
            "      \"longitude\": -743613249\n" +
            "    },\n" +
            "    \"name\": \"\"\n" +
            "  }, {\n" +
            "    \"location\": {\n" +
            "      \"latitude\": 400273442,\n" +
            "      \"longitude\": -741220915\n" +
            "    },\n" +
            "    \"name\": \"\"\n" +
            "  }, {\n" +
            "    \"location\": {\n" +
            "      \"latitude\": 411236786,\n" +
            "      \"longitude\": -744070769\n" +
            "    },\n" +
            "    \"name\": \"\"\n" +
            "  }, {\n" +
            "    \"location\": {\n" +
            "      \"latitude\": 411633782,\n" +
            "      \"longitude\": -746784970\n" +
            "    },\n" +
            "    \"name\": \"211-225 Plains Road, Augusta, NJ 07822, USA\"\n" +
            "  }, {\n" +
            "    \"location\": {\n" +
            "      \"latitude\": 415830701,\n" +
            "      \"longitude\": -742952812\n" +
            "    },\n" +
            "    \"name\": \"\"\n" +
            "  }, {\n" +
            "    \"location\": {\n" +
            "      \"latitude\": 413447164,\n" +
            "      \"longitude\": -748712898\n" +
            "    },\n" +
            "    \"name\": \"165 Pedersen Ridge Road, Milford, PA 18337, USA\"\n" +
            "  }, {\n" +
            "    \"location\": {\n" +
            "      \"latitude\": 405047245,\n" +
            "      \"longitude\": -749800722\n" +
            "    },\n" +
            "    \"name\": \"100-122 Locktown Road, Frenchtown, NJ 08825, USA\"\n" +
            "  }, {\n" +
            "    \"location\": {\n" +
            "      \"latitude\": 418858923,\n" +
            "      \"longitude\": -746156790\n" +
            "    },\n" +
            "    \"name\": \"\"\n" +
            "  }, {\n" +
            "    \"location\": {\n" +
            "      \"latitude\": 417951888,\n" +
            "      \"longitude\": -748484944\n" +
            "    },\n" +
            "    \"name\": \"650-652 Willi Hill Road, Swan Lake, NY 12783, USA\"\n" +
            "  }, {\n" +
            "    \"location\": {\n" +
            "      \"latitude\": 407033786,\n" +
            "      \"longitude\": -743977337\n" +
            "    },\n" +
            "    \"name\": \"26 East 3rd Street, New Providence, NJ 07974, USA\"\n" +
            "  }, {\n" +
            "    \"location\": {\n" +
            "      \"latitude\": 417548014,\n" +
            "      \"longitude\": -740075041\n" +
            "    },\n" +
            "    \"name\": \"\"\n" +
            "  }, {\n" +
            "    \"location\": {\n" +
            "      \"latitude\": 410395868,\n" +
            "      \"longitude\": -744972325\n" +
            "    },\n" +
            "    \"name\": \"\"\n" +
            "  }, {\n" +
            "    \"location\": {\n" +
            "      \"latitude\": 404615353,\n" +
            "      \"longitude\": -745129803\n" +
            "    },\n" +
            "    \"name\": \"\"\n" +
            "  }, {\n" +
            "    \"location\": {\n" +
            "      \"latitude\": 406589790,\n" +
            "      \"longitude\": -743560121\n" +
            "    },\n" +
            "    \"name\": \"611 Lawrence Avenue, Westfield, NJ 07090, USA\"\n" +
            "  }, {\n" +
            "    \"location\": {\n" +
            "      \"latitude\": 414653148,\n" +
            "      \"longitude\": -740477477\n" +
            "    },\n" +
            "    \"name\": \"18 Lannis Avenue, New Windsor, NY 12553, USA\"\n" +
            "  }, {\n" +
            "    \"location\": {\n" +
            "      \"latitude\": 405957808,\n" +
            "      \"longitude\": -743255336\n" +
            "    },\n" +
            "    \"name\": \"82-104 Amherst Avenue, Colonia, NJ 07067, USA\"\n" +
            "  }, {\n" +
            "    \"location\": {\n" +
            "      \"latitude\": 411733589,\n" +
            "      \"longitude\": -741648093\n" +
            "    },\n" +
            "    \"name\": \"170 Seven Lakes Drive, Sloatsburg, NY 10974, USA\"\n" +
            "  }, {\n" +
            "    \"location\": {\n" +
            "      \"latitude\": 412676291,\n" +
            "      \"longitude\": -742606606\n" +
            "    },\n" +
            "    \"name\": \"1270 Lakes Road, Monroe, NY 10950, USA\"\n" +
            "  }, {\n" +
            "    \"location\": {\n" +
            "      \"latitude\": 409224445,\n" +
            "      \"longitude\": -748286738\n" +
            "    },\n" +
            "    \"name\": \"509-535 Alphano Road, Great Meadows, NJ 07838, USA\"\n" +
            "  }, {\n" +
            "    \"location\": {\n" +
            "      \"latitude\": 406523420,\n" +
            "      \"longitude\": -742135517\n" +
            "    },\n" +
            "    \"name\": \"652 Garden Street, Elizabeth, NJ 07202, USA\"\n" +
            "  }, {\n" +
            "    \"location\": {\n" +
            "      \"latitude\": 401827388,\n" +
            "      \"longitude\": -740294537\n" +
            "    },\n" +
            "    \"name\": \"349 Sea Spray Court, Neptune City, NJ 07753, USA\"\n" +
            "  }, {\n" +
            "    \"location\": {\n" +
            "      \"latitude\": 410564152,\n" +
            "      \"longitude\": -743685054\n" +
            "    },\n" +
            "    \"name\": \"13-17 Stanley Street, West Milford, NJ 07480, USA\"\n" +
            "  }, {\n" +
            "    \"location\": {\n" +
            "      \"latitude\": 408472324,\n" +
            "      \"longitude\": -740726046\n" +
            "    },\n" +
            "    \"name\": \"47 Industrial Avenue, Teterboro, NJ 07608, USA\"\n" +
            "  }, {\n" +
            "    \"location\": {\n" +
            "      \"latitude\": 412452168,\n" +
            "      \"longitude\": -740214052\n" +
            "    },\n" +
            "    \"name\": \"5 White Oak Lane, Stony Point, NY 10980, USA\"\n" +
            "  }, {\n" +
            "    \"location\": {\n" +
            "      \"latitude\": 409146138,\n" +
            "      \"longitude\": -746188906\n" +
            "    },\n" +
            "    \"name\": \"Berkshire Valley Management Area Trail, Jefferson, NJ, USA\"\n" +
            "  }, {\n" +
            "    \"location\": {\n" +
            "      \"latitude\": 404701380,\n" +
            "      \"longitude\": -744781745\n" +
            "    },\n" +
            "    \"name\": \"1007 Jersey Avenue, New Brunswick, NJ 08901, USA\"\n" +
            "  }, {\n" +
            "    \"location\": {\n" +
            "      \"latitude\": 409642566,\n" +
            "      \"longitude\": -746017679\n" +
            "    },\n" +
            "    \"name\": \"6 East Emerald Isle Drive, Lake Hopatcong, NJ 07849, USA\"\n" +
            "  }, {\n" +
            "    \"location\": {\n" +
            "      \"latitude\": 408031728,\n" +
            "      \"longitude\": -748645385\n" +
            "    },\n" +
            "    \"name\": \"1358-1474 New Jersey 57, Port Murray, NJ 07865, USA\"\n" +
            "  }, {\n" +
            "    \"location\": {\n" +
            "      \"latitude\": 413700272,\n" +
            "      \"longitude\": -742135189\n" +
            "    },\n" +
            "    \"name\": \"367 Prospect Road, Chester, NY 10918, USA\"\n" +
            "  }, {\n" +
            "    \"location\": {\n" +
            "      \"latitude\": 404310607,\n" +
            "      \"longitude\": -740282632\n" +
            "    },\n" +
            "    \"name\": \"10 Simon Lake Drive, Atlantic Highlands, NJ 07716, USA\"\n" +
            "  }, {\n" +
            "    \"location\": {\n" +
            "      \"latitude\": 409319800,\n" +
            "      \"longitude\": -746201391\n" +
            "    },\n" +
            "    \"name\": \"11 Ward Street, Mount Arlington, NJ 07856, USA\"\n" +
            "  }, {\n" +
            "    \"location\": {\n" +
            "      \"latitude\": 406685311,\n" +
            "      \"longitude\": -742108603\n" +
            "    },\n" +
            "    \"name\": \"300-398 Jefferson Avenue, Elizabeth, NJ 07201, USA\"\n" +
            "  }, {\n" +
            "    \"location\": {\n" +
            "      \"latitude\": 419018117,\n" +
            "      \"longitude\": -749142781\n" +
            "    },\n" +
            "    \"name\": \"43 Dreher Road, Roscoe, NY 12776, USA\"\n" +
            "  }, {\n" +
            "    \"location\": {\n" +
            "      \"latitude\": 412856162,\n" +
            "      \"longitude\": -745148837\n" +
            "    },\n" +
            "    \"name\": \"Swan Street, Pine Island, NY 10969, USA\"\n" +
            "  }, {\n" +
            "    \"location\": {\n" +
            "      \"latitude\": 416560744,\n" +
            "      \"longitude\": -746721964\n" +
            "    },\n" +
            "    \"name\": \"66 Pleasantview Avenue, Monticello, NY 12701, USA\"\n" +
            "  }, {\n" +
            "    \"location\": {\n" +
            "      \"latitude\": 405314270,\n" +
            "      \"longitude\": -749836354\n" +
            "    },\n" +
            "    \"name\": \"\"\n" +
            "  }, {\n" +
            "    \"location\": {\n" +
            "      \"latitude\": 414219548,\n" +
            "      \"longitude\": -743327440\n" +
            "    },\n" +
            "    \"name\": \"\"\n" +
            "  }, {\n" +
            "    \"location\": {\n" +
            "      \"latitude\": 415534177,\n" +
            "      \"longitude\": -742900616\n" +
            "    },\n" +
            "    \"name\": \"565 Winding Hills Road, Montgomery, NY 12549, USA\"\n" +
            "  }, {\n" +
            "    \"location\": {\n" +
            "      \"latitude\": 406898530,\n" +
            "      \"longitude\": -749127080\n" +
            "    },\n" +
            "    \"name\": \"231 Rocky Run Road, Glen Gardner, NJ 08826, USA\"\n" +
            "  }, {\n" +
            "    \"location\": {\n" +
            "      \"latitude\": 407586880,\n" +
            "      \"longitude\": -741670168\n" +
            "    },\n" +
            "    \"name\": \"100 Mount Pleasant Avenue, Newark, NJ 07104, USA\"\n" +
            "  }, {\n" +
            "    \"location\": {\n" +
            "      \"latitude\": 400106455,\n" +
            "      \"longitude\": -742870190\n" +
            "    },\n" +
            "    \"name\": \"517-521 Huntington Drive, Manchester Township, NJ 08759, USA\"\n" +
            "  }, {\n" +
            "    \"location\": {\n" +
            "      \"latitude\": 400066188,\n" +
            "      \"longitude\": -746793294\n" +
            "    },\n" +
            "    \"name\": \"\"\n" +
            "  }, {\n" +
            "    \"location\": {\n" +
            "      \"latitude\": 418803880,\n" +
            "      \"longitude\": -744102673\n" +
            "    },\n" +
            "    \"name\": \"40 Mountain Road, Napanoch, NY 12458, USA\"\n" +
            "  }, {\n" +
            "    \"location\": {\n" +
            "      \"latitude\": 414204288,\n" +
            "      \"longitude\": -747895140\n" +
            "    },\n" +
            "    \"name\": \"\"\n" +
            "  }, {\n" +
            "    \"location\": {\n" +
            "      \"latitude\": 414777405,\n" +
            "      \"longitude\": -740615601\n" +
            "    },\n" +
            "    \"name\": \"\"\n" +
            "  }, {\n" +
            "    \"location\": {\n" +
            "      \"latitude\": 415464475,\n" +
            "      \"longitude\": -747175374\n" +
            "    },\n" +
            "    \"name\": \"48 North Road, Forestburgh, NY 12777, USA\"\n" +
            "  }, {\n" +
            "    \"location\": {\n" +
            "      \"latitude\": 404062378,\n" +
            "      \"longitude\": -746376177\n" +
            "    },\n" +
            "    \"name\": \"\"\n" +
            "  }, {\n" +
            "    \"location\": {\n" +
            "      \"latitude\": 405688272,\n" +
            "      \"longitude\": -749285130\n" +
            "    },\n" +
            "    \"name\": \"\"\n" +
            "  }, {\n" +
            "    \"location\": {\n" +
            "      \"latitude\": 400342070,\n" +
            "      \"longitude\": -748788996\n" +
            "    },\n" +
            "    \"name\": \"\"\n" +
            "  }, {\n" +
            "    \"location\": {\n" +
            "      \"latitude\": 401809022,\n" +
            "      \"longitude\": -744157964\n" +
            "    },\n" +
            "    \"name\": \"\"\n" +
            "  }, {\n" +
            "    \"location\": {\n" +
            "      \"latitude\": 404226644,\n" +
            "      \"longitude\": -740517141\n" +
            "    },\n" +
            "    \"name\": \"9 Thompson Avenue, Leonardo, NJ 07737, USA\"\n" +
            "  }, {\n" +
            "    \"location\": {\n" +
            "      \"latitude\": 410322033,\n" +
            "      \"longitude\": -747871659\n" +
            "    },\n" +
            "    \"name\": \"\"\n" +
            "  }, {\n" +
            "    \"location\": {\n" +
            "      \"latitude\": 407100674,\n" +
            "      \"longitude\": -747742727\n" +
            "    },\n" +
            "    \"name\": \"\"\n" +
            "  }, {\n" +
            "    \"location\": {\n" +
            "      \"latitude\": 418811433,\n" +
            "      \"longitude\": -741718005\n" +
            "    },\n" +
            "    \"name\": \"213 Bush Road, Stone Ridge, NY 12484, USA\"\n" +
            "  }, {\n" +
            "    \"location\": {\n" +
            "      \"latitude\": 415034302,\n" +
            "      \"longitude\": -743850945\n" +
            "    },\n" +
            "    \"name\": \"\"\n" +
            "  }, {\n" +
            "    \"location\": {\n" +
            "      \"latitude\": 411349992,\n" +
            "      \"longitude\": -743694161\n" +
            "    },\n" +
            "    \"name\": \"\"\n" +
            "  }, {\n" +
            "    \"location\": {\n" +
            "      \"latitude\": 404839914,\n" +
            "      \"longitude\": -744759616\n" +
            "    },\n" +
            "    \"name\": \"1-17 Bergen Court, New Brunswick, NJ 08901, USA\"\n" +
            "  }, {\n" +
            "    \"location\": {\n" +
            "      \"latitude\": 414638017,\n" +
            "      \"longitude\": -745957854\n" +
            "    },\n" +
            "    \"name\": \"35 Oakland Valley Road, Cuddebackville, NY 12729, USA\"\n" +
            "  }, {\n" +
            "    \"location\": {\n" +
            "      \"latitude\": 412127800,\n" +
            "      \"longitude\": -740173578\n" +
            "    },\n" +
            "    \"name\": \"\"\n" +
            "  }, {\n" +
            "    \"location\": {\n" +
            "      \"latitude\": 401263460,\n" +
            "      \"longitude\": -747964303\n" +
            "    },\n" +
            "    \"name\": \"\"\n" +
            "  }, {\n" +
            "    \"location\": {\n" +
            "      \"latitude\": 412843391,\n" +
            "      \"longitude\": -749086026\n" +
            "    },\n" +
            "    \"name\": \"\"\n" +
            "  }, {\n" +
            "    \"location\": {\n" +
            "      \"latitude\": 418512773,\n" +
            "      \"longitude\": -743067823\n" +
            "    },\n" +
            "    \"name\": \"\"\n" +
            "  }, {\n" +
            "    \"location\": {\n" +
            "      \"latitude\": 404318328,\n" +
            "      \"longitude\": -740835638\n" +
            "    },\n" +
            "    \"name\": \"42-102 Main Street, Belford, NJ 07718, USA\"\n" +
            "  }, {\n" +
            "    \"location\": {\n" +
            "      \"latitude\": 419020746,\n" +
            "      \"longitude\": -741172328\n" +
            "    },\n" +
            "    \"name\": \"\"\n" +
            "  }, {\n" +
            "    \"location\": {\n" +
            "      \"latitude\": 404080723,\n" +
            "      \"longitude\": -746119569\n" +
            "    },\n" +
            "    \"name\": \"\"\n" +
            "  }, {\n" +
            "    \"location\": {\n" +
            "      \"latitude\": 401012643,\n" +
            "      \"longitude\": -744035134\n" +
            "    },\n" +
            "    \"name\": \"\"\n" +
            "  }, {\n" +
            "    \"location\": {\n" +
            "      \"latitude\": 404306372,\n" +
            "      \"longitude\": -741079661\n" +
            "    },\n" +
            "    \"name\": \"\"\n" +
            "  }, {\n" +
            "    \"location\": {\n" +
            "      \"latitude\": 403966326,\n" +
            "      \"longitude\": -748519297\n" +
            "    },\n" +
            "    \"name\": \"\"\n" +
            "  }, {\n" +
            "    \"location\": {\n" +
            "      \"latitude\": 405002031,\n" +
            "      \"longitude\": -748407866\n" +
            "    },\n" +
            "    \"name\": \"\"\n" +
            "  }, {\n" +
            "    \"location\": {\n" +
            "      \"latitude\": 409532885,\n" +
            "      \"longitude\": -742200683\n" +
            "    },\n" +
            "    \"name\": \"\"\n" +
            "  }, {\n" +
            "    \"location\": {\n" +
            "      \"latitude\": 416851321,\n" +
            "      \"longitude\": -742674555\n" +
            "    },\n" +
            "    \"name\": \"\"\n" +
            "  }, {\n" +
            "    \"location\": {\n" +
            "      \"latitude\": 406411633,\n" +
            "      \"longitude\": -741722051\n" +
            "    },\n" +
            "    \"name\": \"3387 Richmond Terrace, Staten Island, NY 10303, USA\"\n" +
            "  }, {\n" +
            "    \"location\": {\n" +
            "      \"latitude\": 413069058,\n" +
            "      \"longitude\": -744597778\n" +
            "    },\n" +
            "    \"name\": \"261 Van Sickle Road, Goshen, NY 10924, USA\"\n" +
            "  }, {\n" +
            "    \"location\": {\n" +
            "      \"latitude\": 418465462,\n" +
            "      \"longitude\": -746859398\n" +
            "    },\n" +
            "    \"name\": \"\"\n" +
            "  }, {\n" +
            "    \"location\": {\n" +
            "      \"latitude\": 411733222,\n" +
            "      \"longitude\": -744228360\n" +
            "    },\n" +
            "    \"name\": \"\"\n" +
            "  }, {\n" +
            "    \"location\": {\n" +
            "      \"latitude\": 410248224,\n" +
            "      \"longitude\": -747127767\n" +
            "    },\n" +
            "    \"name\": \"3 Hasta Way, Newton, NJ 07860, USA\"\n" +
            "  }]\n" +
            "}\n";

    private List<Feature> features;

    public Features() {
        try {
            FeatureDatabase.Builder database = FeatureDatabase.newBuilder();
            JsonFormat.parser().merge(JSON, database);
            features = database.getFeatureList();
        } catch (InvalidProtocolBufferException ignore) {
            features = emptyList();
        }
    }

    @Override
    public Iterator<Feature> iterator() {
        return features.iterator();
    }

    public int size() {return features.size();}

    public Feature get(int index) {return features.get(index);}
}
