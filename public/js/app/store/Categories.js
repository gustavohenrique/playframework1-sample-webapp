Ext.define('PoupaNiquel.store.Categories', {
    extend: 'Ext.data.Store',
    model: 'PoupaNiquel.model.Category',
    
    storeId: 'Categories',
    autoLoad: true,
    autoSync: false,
    pageSize: 30,
    
    proxy: {
        type: 'rest',
        api: {
        	read : '/categories/read',
        	create : '/categories/create',
            update: '/categories/update',
            destroy: '/categories/delete'
        },
        reader: {
            type: 'json',
            root: 'data',
            successProperty: 'success'
        },
        writer: {
            type: 'json',
            root: 'data',
        },
        
        listeners: {
            exception: function(proxy, response, operation){
            	var message = Ext.JSON.decode(response.responseText).message;
            	Ext.Msg.alert('Error', message);
            }
        }
    }
});