Ext.define('PoupaNiquel.store.Users', {
    extend: 'Ext.data.Store',
    model: 'PoupaNiquel.model.User',
    
    storeId: 'Users',
    autoLoad: false,
    autoSync: true,
    pageSize: 35,
    
    proxy: {
        type: 'rest',
        api: {
        	create : '/auth/authenticate',
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
    }
});