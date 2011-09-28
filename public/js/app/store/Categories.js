Ext.define('PoupaNiquel.store.Categories', {
    extend: 'Ext.data.Store',
    model: 'PoupaNiquel.model.Generic',
    
    storeId: 'Categories',
    autoLoad: true,
    pageSize: 1000,
    
    proxy: {
        type: 'ajax',
        api: {
        	read : '/transactions/categories',
        },
        reader: {
            type: 'json',
            root: 'data',
            successProperty: 'success'
        },
    
        listeners: {
            exception: function(proxy, response, operation){
                //alert(operation.getError());
            }
        }
    }
});