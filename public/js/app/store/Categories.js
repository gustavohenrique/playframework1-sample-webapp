Ext.define('PoupaNiquel.store.Categories', {
    extend: 'Ext.data.Store',
    model: 'PoupaNiquel.model.Generic',
    
    storeId: 'Categories',
    autoLoad: true,
    autoSync: false,
    pageSize: 1000,
    
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
        }
    }
});