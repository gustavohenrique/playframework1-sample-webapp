Ext.define('PoupaNiquel.store.Accounts', {
    extend: 'Ext.data.Store',
    model: 'PoupaNiquel.model.Account',
    
    storeId: 'Accounts',
    autoLoad: true,
    autoSync: true,
    pageSize: 35,
    
    proxy: {
        type: 'rest',
        api: {
        	read : '/accounts/read',
        	create : '/accounts/create',
            update: '/accounts/update',
            destroy: '/accounts/delete'
        },
        reader: {
            type: 'json',
            root: 'data',
            successProperty: 'success'
        },
        writer: {
            type: 'json'
        },
    }
});