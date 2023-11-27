package com.br.digital_hoteis.domain.entity;

public enum Facilities {

    // Facilidades de Banheiro
    BATHROOM("Banheiro"),
    BATHTUB("Banheira", BATHROOM),
    TOILET_PAPER("Papel higienico", BATHROOM),
    BATHROBE("Roupão de banho", BATHROOM),
    TOWELS("Toalhas", BATHROOM),
    PRIVATE_BATHROOM("Casa de banho privativa", BATHROOM),
    WC("Vaso sanitário", BATHROOM),
    SHOWER("Chuveiro", BATHROOM),
    FREE_TOILETRIES("Itens de banho", BATHROOM),
    HAIRDRYER("Secador", BATHROOM),

    BEDROOM("Quarto"),
    LINENS("Roupa de cama", BEDROOM),
    WARDROBE("Guarda roupas", BEDROOM),


    // Mídia / Tecnologia
    MEDIA_AND_TECHNOLOGY("Media e Tecnologia"),
    STREAMING_SERVICE("Serviço de streaming (ex.: Netflix)", MEDIA_AND_TECHNOLOGY),
    FLAT_SCREEN_TV("Televisão de tela plana", MEDIA_AND_TECHNOLOGY),
    CABLE_TV("Televisão a cabo", MEDIA_AND_TECHNOLOGY),
    TELEPHONE("Telefone", MEDIA_AND_TECHNOLOGY),

    // Ar condicionado
    AIR_CONDITIONER("Ar condicionado"),

    // Wifi
    FREE_WIFI("Acesso Wi-Fi disponível por todo o hotel. Custo: Gratuito"),

    PET_FRIENDLY("Animais permitidos. Poderão haver cobranças extras"),

    // Cozinha
    KITCHEN("Cozinha"),
    CLEANING_PRODUCTS("Produtos de limpeza", KITCHEN),
    STOVE_TOP("Fogão", KITCHEN),
    MICROWAVE("Microondas", KITCHEN),
    REFRIGERATOR("Geladeira", KITCHEN),

    // Sala
    LIVING_AREA("Área de estar"),
    DINING_AREA("Área de jantar", LIVING_AREA),
    SOFA("Sofá", LIVING_AREA),
    DESK("Mesa", LIVING_AREA),

    // Comodidades do quarto
    ROOM_AMENITIES("Comodidades do quarto"),
    SOCKET_NEAR_BED("Tomada perto da cama", ROOM_AMENITIES),
    TILE_MARBLE_FLOOR("Piso em azulejo/mármore", ROOM_AMENITIES),
    PRIVATE_ENTRANCE("Entrada privativa", ROOM_AMENITIES),
    IRON("Ferro de passar", ROOM_AMENITIES),

    // Serviços de Limpeza
    CLEANING_SERVICES("Serviços de Limpeza"),
    DAILY_HOUSEKEEPING("Limpeza diária", CLEANING_SERVICES),
    IRONING_SERVICE("Serviço de passar roupa (com custo adicional)", CLEANING_SERVICES),
    DRY_CLEANING("Lavagem a seco (com custo adicional)", CLEANING_SERVICES),
    LAUNDRY("Lavanderia (com custo adicional)", CLEANING_SERVICES),

    // Serviços da Recepção
    FRONT_DESK_SERVICES("Serviços da Recepção"),
    LOCKERS("Armários", FRONT_DESK_SERVICES),
    CONCIERGE("Concierge", FRONT_DESK_SERVICES),
    BAGGAGE_STORAGE("Guarda de bagagem", FRONT_DESK_SERVICES),
    CURRENCY_EXCHANGE("Câmbio", FRONT_DESK_SERVICES),
    TWENTY_FOUR_HOUR_FRONT_DESK("Recepção 24 horas", FRONT_DESK_SERVICES),

    // Spa
    SPA("Spa"),
    FITNESS("Academia", SPA),
    SPA_LOUNGE("Área de relaxamento no spa", SPA),
    STEAM_ROOM("Sala de vapor", SPA),
    BEACH_LOUNGERS("Cadeiras de praia", SPA),
    SOLARIUM("Solário", SPA),
    SAUNA("Sauna", SPA),

    // Acessibilidade
    ACCESSIBILITY("Acessibilidade"),
    LOWERED_SINK("Pia rebaixada", ACCESSIBILITY),
    RAISED_TOILET("Vaso sanitário elevado", ACCESSIBILITY),
    TOILET_WITH_GRAB_RAILS("Vaso sanitário com barras de apoio", ACCESSIBILITY),
    WHEELCHAIR_ACCESSIBLE("Acessível para cadeira de rodas", ACCESSIBILITY),
    UPPER_FLOORS_ACCESSIBLE_BY_ELEVATOR("Andares superiores acessíveis por elevador", ACCESSIBILITY),

    // Segurança
    SAFETY_SECURITY("Segurança"),
    FIRE_EXTINGUISHERS("Extintores de incêndio", SAFETY_SECURITY),
    CCTV_OUTSIDE_PROPERTY("CCTV fora da propriedade", SAFETY_SECURITY),
    CCTV_IN_COMMON_AREAS("CCTV em áreas comuns", SAFETY_SECURITY),
    SMOKE_ALARMS("Detectores de fumaça", SAFETY_SECURITY),
    SECURITY_ALARM("Alarme de segurança", SAFETY_SECURITY),
    KEY_CARD_ACCESS("Acesso com cartão-chave", SAFETY_SECURITY),
    TWENTY_FOUR_HOUR_SECURITY("Segurança 24 horas", SAFETY_SECURITY),
    SAFE("Cofre", SAFETY_SECURITY),

    // Comida e Bebida
    FOOD_DRINK("Comida e Bebida"),
    FRUIT_WITH_ADDITIONAL_CHARGE("Fruta (com custo adicional)", FOOD_DRINK),
    WINE_CHAMPAGNE_WITH_ADDITIONAL_CHARGE("Vinho/Champanhe (com custo adicional)", FOOD_DRINK),
    BAR("Bar", FOOD_DRINK),
    MINIBAR("Minibar", FOOD_DRINK),
    RESTAURANT("Restaurante", FOOD_DRINK),

    // Idiomas Falados
    LANGUAGES_SPOKEN("Idiomas Falados"),
    ENGLISH("Inglês", LANGUAGES_SPOKEN),
    PORTUGUESE("Português", LANGUAGES_SPOKEN),
    SPANISH("Espanhol", LANGUAGES_SPOKEN);

    private final String description;
    private final Facilities parent;

    Facilities(String description) {
        this(description, null);
    }

    Facilities(String description, Facilities parent) {
        this.description = description;
        this.parent = parent;
    }

    public String getDescription() {
        return description;
    }

    public Facilities getParent() {
        return parent;
    }
}

