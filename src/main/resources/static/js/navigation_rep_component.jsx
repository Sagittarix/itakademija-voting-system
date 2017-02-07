var Link = window.ReactRouter.Link;

var NavLink = function( props, context ) {
    var isActive = context.router.isActive( props.to );
    var className = isActive ? 'active' : '';

    return (
        <li className={className}>
            <Link {...props}>
                {props.children}
            </Link>
        </li>
    );
};

NavLink.contextTypes = {
    router: React.PropTypes.object,
};

NavLink.propTypes = {
    children: React.PropTypes.node.isRequired,
    to: React.PropTypes.string.isRequired,
};

var NavigationRepComponent = React.createClass( {
    render: function() {
        return (
            <nav className="navbar navbar-default">
                <div className="container-fluid">
                    <ul className="nav navbar-nav">
                        <NavLink to="/home" onlyActiveOnIndex>Home</NavLink>
                        <NavLink to="/reg-votes-multi">Balsų registravimas (daug.)</NavLink>
                        <NavLink to="/reg-votes-single">Balsų registravimas (vien.)</NavLink>
                    </ul>
                    <ul className="nav navbar-nav navbar-right">
                        <button type="button" className="btn btn-default" onClick={this.props.onLogoutClick}>Atsijungti</button>
                    </ul>
                </div>
            </nav>
        );
    }
});

window.NavigationRepComponent = NavigationRepComponent;