[#ftl]
[@c.page]
    <div class="detail">
        <div class="vizitka">
            <div class="jmeno">${vizitka.celeJmeno}</div>
            <div class="firma">${vizitka.firma}</div>
            <div class="udaje">
                <div class="adresa">
                    <div>${vizitka.ulice}</div>
                    <div><span>${vizitka.psc}</span> <span>${vizitka.obec}</span></div>
                </div>
                <div class="kontakty">
                    <div><i class="las la-at"></i> <span>${vizitka.email}</span></div>
                    <div><i class="las la-phone-alt"></i> <span>${vizitka.telefon}</span></div>
                    <div><i class="las la-globe"></i> <span>${vizitka.web}</span></div>
                </div>
            </div>
        </div>

        <div class="mapa">
            <iframe style="border:none" src="https://frame.mapy.cz/?q=${vizitka.celaAdresa?url}" width="100%" height="100%"
                    frameborder="0"></iframe>
        </div>

        [@c.back /]
    </div>
[/@c.page]
