Ext.define('PoupaNiquel.store.Accounts', {
    extend: 'Ext.data.Store',
    model: 'PoupaNiquel.model.Account',
    
    storeId: 'Accounts',
    autoLoad: true,
    autoSync: false,
    pageSize: 30,
    
    proxy: Ext.create('PoupaNiquel.store.CommonProxy', {
    	model: 'Account',
        api: {
        	read : '/accounts/read',
        	create : '/accounts/create',
            update: '/accounts/update',
            destroy: '/accounts/delete'
        }
    }),
});