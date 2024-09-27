import request  from "@/utils/request.js"

export function listBrand(query){
    return request({
        url: `/product/brand/list`,
        method: 'get',
        params: query
    })
}