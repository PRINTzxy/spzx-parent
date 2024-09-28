/**
 * params传递的参数不会显示在URL，类似POST请求，而query参数会显示并用?连接，类似GET请求。
 * params传递需配合/:paramName，否则刷新会丢失数据。使用params时，路由配置应包含对应的param。
 * query传参会显示在URL，保密性较差，适合不敏感数据。组件中可通过props接收params和query参数。
 * $route用于获取当前路由信息，$router用于导航操作
 * @param query
 * @returns {*}
 */
import request from "../../utils/request.js";
//查询品牌列表
export function listBrand(query){
    return request({
        url: `/product/brand/list`,
        method: 'get',
        params: query
    })
}
//新增品牌
export function addBrand(data){
    return request({
        url: '/product/brand',
        method: 'post',
        data: data
    })
}
// //查询品牌详细
export function getBrand(id){
    return request({
        url: '/product/brand/' + id,
        method: 'get'
    })
}
//修改品牌
export function updateBrand(data){
    return request({
        url: '/product/brand',
        method: 'put',
        data: data
    })
}
//删除品牌
export function delBrand(id) {
    return request({
      url: '/product/brand/' + id,
      method: 'delete'
    })
}



