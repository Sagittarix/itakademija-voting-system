var App = React.createClass( {
    render: function() {
        return (
            <div style={{ paddingTop: '10px' }}>
                <a href="/"><img style={{ paddingBottom: '10px' }} src="images/vrk-logo-lt.png" alt="vrk logo" /></a>
                <NavigationAdminContainer />
                {this.props.children}
                <div className="container footer navbar-fixed-bottom" style={{backgroundColor: '#660000'}}>&copy; KALM, 2017</div>
            </div>
        );
    }
});

var NoMatch = React.createClass( {
    render: function() {
        return <div><h4>Atsiprašome puslapis nerastas</h4></div>;
    }
});

var Router = ReactRouter.Router;
var Route = ReactRouter.Route;
var IndexRoute = ReactRouter.IndexRoute;
var hashHistory = ReactRouter.hashHistory;

ReactDOM.render((
    <Router history={hashHistory}>
        <Route path="/" component={App}>
            <IndexRoute component={HomeComponent} />
            <Route path="/home" component={HomeComponent} />
            <Route path="/con" component={ConstituencyListContainer} />
            <Route path="/dis/:conId" component={DistrictListContainer} />
            <Route path="/add-con" component={AddConstituencyContainer} />
            <Route path="/add-dis/:conId" component={AddDistrictContainer} />
            <Route path="/add-rep/:conId/:disId" component={AddRepresentativeContainer} />

            <Route path="/repres/:conId/:repId" component={RepresentativeInfoContainer} />
            <Route path="/parties" component={PartyListContainer} />
            <Route path="/add-party" component={AddPartyContainer} />
            <Route path="/publish-delete-votes" component={PubDelVotesConstituencyListContainer} />
            <Route path="/publish-delete-votes/:conId" component={PubDelVotesDistrictListContainer} />
            <Route path="/upload-single-cadidates" component={AdministrateSingleCandidatesContainer} />
            <Route path="/upload-multi-cadidates" component={AdministrateMultiCandidatesContainer} />
            <Route path="/change-pass" component={ChangePasswordContainer} />
            <Route path="*" component={NoMatch} />
        </Route>
    </Router>
), document.getElementById( 'root_admin' ) );
